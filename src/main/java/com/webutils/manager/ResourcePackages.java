package com.webutils.manager;

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

import com.utils.JsonUtil;
import com.webutils.app.WebAppClient;

public class ResourcePackages {

	public static final String IS_FOLDER = "(\\/[a-zA-Z0-9_.-]+)+\\/";
	public static final String MODULE_FILE = "/module.properties";
	public static final String FILE_KEY = "files";
	public static final String AT_KEY = "@";
	public static final String AT_SEPERATOR = ",";
	public static final String EMPTY_SLASH = "/";
	public static final String EXT_CSS = ".css";
	public static final String EXT_JS = ".js";
	public static final String UTIL_RESOLVE_PACK_START = "(";
	public static final String UTIL_RESOLVE_PACK_END = ")";

	private Map<String, String> moduleFiles = new Hashtable<String, String>();

	private Map<String, Map<String, List<String>>> moduleCache = new Hashtable<String, Map<String, List<String>>>();
	private Long lastScanTime = 0L;

	public void scanResources(ServletContext context) {
		this.scanPacks(context, WebAppClient.getWebAppProperties()
				.getStaticLibPath());
		this.scanPacks(context, WebAppClient.getWebAppProperties()
				.getStaticAppPath());
		this.lastScanTime = (new Date()).getTime();
	}

	public Long getLastScanTime() {
		return lastScanTime;
	}

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
	 * @param fileFolder
	 * @param ent
	 * @return
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

	public String writePackageInfo(String cb, String filesMapString) {
		return ((cb == null) ? UTIL_RESOLVE_PACK_START
				: (cb + UTIL_RESOLVE_PACK_START))
				+ filesMapString
				+ UTIL_RESOLVE_PACK_END;
	}

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

	public String getModulePath(String module) {
		String[] splittedpath = module.split(EMPTY_SLASH);
		return moduleFiles.get(splittedpath[splittedpath.length - 1]);
	}
}
