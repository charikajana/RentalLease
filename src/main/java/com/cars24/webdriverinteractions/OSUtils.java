package com.cars24.webdriverinteractions;

public class OSUtils {
	private static String OS = null;

	public static String getOsName() {
		if (OS == null) {
			OS = System.getProperty("os.name").toLowerCase();
		}
		return OS;
	}
	public static boolean isWindows() {
		return (getOsName().indexOf("win") >= 0);
	}
	public static boolean isMac() {
		return (getOsName().indexOf("mac") >= 0);
	}
	public static boolean isUnix() {
		return (getOsName().indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0);
	}
	public static boolean isSolaris() {
		return (getOsName().indexOf("sunos") >= 0);
	}

}
