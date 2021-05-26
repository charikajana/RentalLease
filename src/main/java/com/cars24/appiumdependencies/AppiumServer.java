package com.cars24.appiumdependencies;

import java.io.File;
import java.util.Random;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class AppiumServer {
	public static int portNumber;
	WebDriver driver;
	DesiredCapabilities capabilities;
	public static AppiumServer appiumserver;
	/**
	 * AppiumDriverLocalService is Final class
	 */

	private static AppiumDriverLocalService service;

	public AppiumServer(String appiumserverpath) {
		String osName = System.getProperty("os.name");

		if (osName.contains("Windows")) {
			service = AppiumDriverLocalService.buildService(new AppiumServiceBuilder().usingPort(getportNumber())
					.usingDriverExecutable(
							new File(appiumserverpath + File.separator + "Appium" + File.separator + "node.exe"))
					.withAppiumJS(new File(appiumserverpath + File.separator + "Appium" + File.separator
							+ "node_modules" + File.separator + "appium" + File.separator + "bin" + File.separator
							+ "appium.js")));
		} else if (osName.contains("Mac")) {
			service = AppiumDriverLocalService.buildService(new AppiumServiceBuilder().usingPort(getportNumber())
					.usingDriverExecutable(new File("/Applications/Appium.app/Contents/Resources/node/bin/node"))
					.withAppiumJS(
							new File("/Applications/Appium.app/Contents/Resources/node_modules/appium/bin/appium.js")));

		} else {
			Assert.fail("Starting appium is not supporting the current OS.");
		}
	}

	/**
	 * Starts appium server
	 */

	public static void startAppiumServer(String appiumserverpath) {
		appiumserver = new AppiumServer(appiumserverpath);
		service.start();
	}

	/**
	 * Stops appium server
	 */
	public static void stopAppiumServer() {
		service.stop();
	}

	public static int getportNumber() {
		int randomInt = 0;
		int portnumber = 4700;
		Random randomGenerator = new Random();
		for (int i = 1; i <= 1; ++i) {
			randomInt = randomGenerator.nextInt(99);
			portNumber = portnumber + randomInt;
		}
		return portNumber;
	}
}
