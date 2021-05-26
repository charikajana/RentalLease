package com.cars24.fileoperations;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.openqa.selenium.By;

public class LoadProperties {
	public static By by;
	static Properties pro = new Properties();
	public static String Results_Path;
	public static String ScreenShots_Path;
	public static boolean loadallproperties;

	public static Properties loadproperties() {
		if (pro == null) {
			try {
				File f = new File(System.getProperty("user.dir") + "/pageobjects/");
				File[] file = f.listFiles();
				for (int i = 0; i < file.length; i++) {
					pro.load(new FileInputStream(file[i]));
				}
			} catch (Exception e) {
				return null;
			}
		}else {
			if(loadallproperties) {
				try {
					File f = new File(System.getProperty("user.dir") + "/pageobjects/");
					File[] file = f.listFiles();
					for (int i = 0; i < file.length; i++) {
						pro.load(new FileInputStream(file[i]));
					}
					loadallproperties=false;
				} catch (Exception e) {
					return null;
				}
			}
		}
		return pro;
	}

	public static String DerivergetProperty(String prop) {
		pro = loadproperties();
		return pro.getProperty(prop);
	}

	public static String getbyproperty(String prop, String bystring) {
		String property = null;
		pro = loadproperties();
		if (bystring.equalsIgnoreCase("bytype")) {
			property = pro.getProperty(prop).split(";")[0];
		} else {
			property = pro.getProperty(prop).split(";")[1];
		}
		return property;
	}

	public static void createReportsfolder() {
		Date date = new Date();
		Results_Path = System.getProperty("user.dir") + "/Reports/"
				+ new SimpleDateFormat("MMM:dd").format(date).replace(":", "_") + "/Result@_"
				+ new SimpleDateFormat("HH:mm:ss").format(date).replace(":", "-");
		File file = new File(Results_Path);
		if (!file.exists()) {
			file.mkdirs();
		}
		ScreenShots_Path = Results_Path + "/Screenshots/";
		File files = new File(ScreenShots_Path);
		if (!files.exists()) {
			files.mkdirs();
		}

	}

	public static void main(String[] args) {
		Loadenvironmentvariables("DEV");
	}

	public static Properties Loadenvironmentvariables(String envi) {		
		try {
			pro.load(new FileInputStream(new File(System.getProperty("user.dir")
					+ "/src/test/resources/environmentvariables/" + envi + ".properties")));
			loadallproperties=true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return pro;
	}
	
	public static By getbyarrgument(String propertry) throws FileNotFoundException, IOException {
		String bylocator = LoadProperties.getbyproperty(propertry, "bytype");
		String byvalue = LoadProperties.getbyproperty(propertry, "byvalue");
		switch (bylocator.toUpperCase()) {
		case "ID":
			by = By.id(byvalue);
			break;
		case "NAME":
			by = By.name(byvalue);
			break;
		case "XPATH":
			by = By.xpath(byvalue);
			break;
		case "CLASSNAME":
			by = By.className(byvalue);
			break;
		case "LINKTEXT":
			by = By.linkText(byvalue);
			break;
		case "PARTIALLINKTEXT":
			by = By.partialLinkText(byvalue);
			break;
		case "CSSSELECTOR":
			by = By.cssSelector(byvalue);
			break;

		}
		return by;
	}

}
