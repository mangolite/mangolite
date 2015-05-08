package org.spamjs.mangolite.tags;

/**
 * Contains utility functions used for custom tags.
 * 
 * @author <a href="mailto:lalit.tanwar07@gmail.com">Lalit Tanwar</a>
 * @version 1.3
 * @since Nov 17, 2014
 * 
 */
public final class TagsUtil {

	/** The Constant TEN. */
	private static final int TEN = 10;

	/** The Constant HUNDRED. */
	private static final int HUNDRED = 100;

	/**
	 * Instantiates a new tags util.
	 */
	private TagsUtil() {
		throw new IllegalStateException("This is a class with static methods and should not be instantiated");
	}

	/**
	 * Method to find round value.
	 *
	 * @param val
	 *            the val
	 * @param rounding
	 *            the rounding
	 * @return the double
	 */
	public static double round(double val, int rounding) {
		double p = (double) Math.pow(TEN, rounding);
		double tmp = Math.round(val * p);
		return (double) tmp / p;
	}

	/**
	 * Method to find out percentage.
	 *
	 * @param value
	 *            the value
	 * @return the double
	 */
	public static double percent(double value) {
		return value * HUNDRED;
	}
}