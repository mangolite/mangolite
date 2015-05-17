package org.spamjs.mangolite;

import org.mozilla.javascript.ErrorReporter;
import org.mozilla.javascript.EvaluatorException;
import org.spamjs.utils.Log;

// TODO: Auto-generated Javadoc
/**
 * Error log class.
 *
 * @author <a href="mailto:lalit.tanwar07@gmail.com">Lalit Tanwar</a>
 */
public class ResourceMinifyFilterErrorReporter implements ErrorReporter {
	
	/** The Constant log. */
	private static final Log log = new Log();

	/** The errors. */
	private StringBuilder errors = new StringBuilder();

	/**
	 * Instantiates a new resource minify filter error reporter.
	 *
	 * @param requestURI the request uri
	 */
	public ResourceMinifyFilterErrorReporter(String requestURI) {
		super();
		errors.append(requestURI);
	}

	/* (non-Javadoc)
	 * @see org.mozilla.javascript.ErrorReporter#warning(java.lang.String, java.lang.String, int, java.lang.String, int)
	 */
	public void warning(String message, String sourceName, int line,
			String lineSource, int lineOffset) {
		if (line < 0) {
			log.warn(message);
		} else {
			log.warn(line + ':' + lineOffset + ':' + message);
		}
	}

	/* (non-Javadoc)
	 * @see org.mozilla.javascript.ErrorReporter#error(java.lang.String, java.lang.String, int, java.lang.String, int)
	 */
	public void error(String message, String sourceName, int line,
			String lineSource, int lineOffset) {
		String msg;
		if (line < 0) {
			msg = message;
		} else {
			msg = "\n"+lineSource + "  (" + message + ") [" + line + ','
					+ lineOffset + "]";
		}
		log.error(msg);
		errors.append(msg);
	}

	/* (non-Javadoc)
	 * @see org.mozilla.javascript.ErrorReporter#runtimeError(java.lang.String, java.lang.String, int, java.lang.String, int)
	 */
	public EvaluatorException runtimeError(String message, String sourceName,
			int line, String lineSource, int lineOffset) {
		error(message, sourceName, line, lineSource, lineOffset);
		return new EvaluatorException(message);
	}

	/**
	 * Gets the error.
	 *
	 * @return the error
	 */
	public String getError() {
		return errors.toString();
	}
}
