package org.spamjs.mangolite.manager;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.ServletContext;

import org.ini4j.Ini;
import org.ini4j.InvalidFileFormatException;
import org.spamjs.mangolite.app.WebAppClient;
import org.spamjs.utils.JsonUtil;

// TODO: Auto-generated Javadoc
/**
 * The Class ResourcePackages.
 */
public class ResourcePackages {

	/** The Constant IS_FOLDER. */
	public static final String IS_FOLDER = "(\\/[a-zA-Z0-9_.-]+)+\\/";

	/** The Constant MODULE_FILE. */
	public static final String MODULE_FILE = "/module.properties";

	/** The Constant FILE_KEY. */
	public static final String FILE_KEY = "files";

	/** The Constant AT_KEY. */
	public static final String AT_KEY = "@";

	/** The Constant AT_SEPERATOR. */
	public static final String AT_SEPERATOR = ",";

	/** The Constant EMPTY_SLASH. */
	public static final String EMPTY_SLASH = "/";

	/** The Constant EXT_CSS. */
	public static final String EXT_CSS = ".css";

	/** The Constant EXT_JS. */
	public static final String EXT_JS = ".js";

	/** The Constant UTIL_RESOLVE_PACK_START. */
	public static final String UTIL_RESOLVE_PACK_START = "(";

	/** The Constant UTIL_RESOLVE_PACK_END. */
	public static final String UTIL_RESOLVE_PACK_END = ")";

	/** The module files. */
	private Map<String, String> moduleFiles = new Hashtable<String, String>();

	/** The module cache. */
	private Map<String, Map<String, List<String>>> moduleCache = new Hashtable<String, Map<String, List<String>>>();

	/** The last scan time. */
	private Long lastScanTime = 0L;

	/**
	 * Scan resources.
	 *
	 * @param context
	 *            the context
	 */
	public void scanResources(ServletContext context) {
		this.scanPacks(context, WebAppClient.getWebAppProperties()
				.getStaticLibPath());
		this.scanPacks(context, WebAppClient.getWebAppProperties()
				.getStaticAppPath());
		this.lastScanTime = (new Date()).getTime();
	}

	/**
	 * Gets the last scan time.
	 *
	 * @return the last scan time
	 */
	public Long getLastScanTime() {
		return lastScanTime;
	}

	/**
	 * Scan packs.
	 *
	 * @param context
	 *            the context
	 * @param path
	 *            the path
	 */
	public void scanPacks(ServletContext context, String path) {
		Set<String> paths = context.getResourcePaths(path);
		if (paths != null) {
			for (String ipath : paths) {
				if (ipath.matches(IS_FOLDER)) {
					scanPacks(context, ipath);
				} else if (ipath.endsWith(MODULE_FILE)) {
					try {
						scanFile(context, ipath);
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else if (ipath.endsWith(EXT_JS) || ipath.endsWith(EXT_CSS)) {
					String[] splittedpath = ipath.split(EMPTY_SLASH);
					moduleFiles.put(splittedpath[splittedpath.length - 1],
							ipath);
				}
			}
		}
	}

	/**
	 * Scan file.
	 *
	 * @param context
	 *            the context
	 * @param filePath
	 *            the file path
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws InvalidFileFormatException
	 *             the invalid file format exception
	 */
	@SuppressWarnings("unchecked")
	private void scanFile(ServletContext context, String filePath)
			throws IOException, InvalidFileFormatException {
		Ini ini = new Ini(context.getResourceAsStream(filePath));
		Map<String, Object> packs = JsonUtil.toMap(ini);
		String fileFolder = filePath.replaceAll(MODULE_FILE, EMPTY_SLASH);
		for (Entry<String, Object> packsEntry : packs.entrySet()) {
			Map<String, List<String>> formattedPack = parsePack(fileFolder,
					(Map<String, String>) packsEntry.getValue());
			this.moduleCache.put(packsEntry.getKey(), formattedPack);
		}
	}

	/**
	 * Parses the pack.
	 *
	 * @param fileFolder
	 *            the file folder
	 * @param pack
	 *            the pack
	 * @return the map
	 */
	private Map<String, List<String>> parsePack(String fileFolder,
			Map<String, String> pack) {
		Map<String, List<String>> formattedPack = new HashMap<String, List<String>>();
		List<String> packFiles = new LinkedList<String>();
		List<String> dependsOn = null;
		for (Entry<String, String> packEntry : pack.entrySet()) {
			String file = packEntry.getValue();
			if (AT_KEY.equals(packEntry.getKey())) {
				dependsOn = Arrays.asList(file.split(AT_SEPERATOR));
			} else {
				packFiles.add(WebAppClient.getWebAppProperties()
						.getAppContext() + fileFolder + file);
			}
		}
		formattedPack.put(FILE_KEY, packFiles);
		formattedPack.put(AT_KEY, dependsOn);
		return formattedPack;
	}

	/**
	 * Write packs.
	 *
	 * @param packs
	 *            the packs
	 * @param cb
	 *            the cb
	 * @return the string
	 */
	public String writePacks(String[] packs, String cb) {
		Map<String, Boolean> packMap = new HashMap<String, Boolean>();
		Map<String, Object> filesMap = new LinkedHashMap<String, Object>();

		for (String packName : packs) {
			if ("*".equalsIgnoreCase(packName))
				return writePackageInfo(cb, JsonUtil.toJson(moduleCache));
			getPack(packName, packMap, filesMap);
		}
		return writePackageInfo(cb, JsonUtil.toJson(filesMap));
	}

	/**
	 * Write package info.
	 *
	 * @param cb
	 *            the cb
	 * @param filesMapString
	 *            the files map string
	 * @return the string
	 */
	public String writePackageInfo(String cb, String filesMapString) {
		return ((cb == null) ? UTIL_RESOLVE_PACK_START
				: (cb + UTIL_RESOLVE_PACK_START))
				+ filesMapString
				+ UTIL_RESOLVE_PACK_END;
	}

	/**
	 * Gets the pack.
	 *
	 * @param packName
	 *            the pack name
	 * @param packMap
	 *            the pack map
	 * @param filesMap
	 *            the files map
	 */
	public void getPack(String packName, Map<String, Boolean> packMap,
			Map<String, Object> filesMap) {
		if (packMap.get(packName) == null) {
			Map<String, List<String>> formattedPack = (Map<String, List<String>>) moduleCache
					.get(packName);
			if (formattedPack != null) {
				if (formattedPack.get(AT_KEY) != null) {
					for (String ipackName : formattedPack.get(AT_KEY)) {
						getPack(ipackName, packMap, filesMap);
					}
				}
				filesMap.put(packName, formattedPack);
				/*
				 * if (formattedPack.get(FILE_KEY) != null) { for (String ifiles
				 * : formattedPack.get(FILE_KEY)) { filesMap.put(ifiles,
				 * ifiles); } }
				 */
			}
			packMap.put(packName, Boolean.TRUE);
		}
	};

	/**
	 * Gets the module path.
	 *
	 * @param module
	 *            the module
	 * @return the module path
	 */
	public String getModulePath(String module) {
		String[] splittedpath = module.split(EMPTY_SLASH);
		return moduleFiles.get(splittedpath[splittedpath.length - 1]);
	}
}
