package utils;

import java.util.logging.Logger;

public class Utils {
	private static Logger LOGGER = getLogger(Utils.class.getName());
	public static Logger getLogger(String className) {
		return Logger.getLogger(className);
	}
}
