package com.cars24.browserfactory;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserFactory {
	static WebDriver driver;

	public static WebDriver getbrowser(String BrowserName) {
		String Browser = System.getProperty("Browser");
		if (Browser != null) {
			BrowserName = Browser;
		}
		switch (BrowserName.toUpperCase()) {
		case "CHROME":
			driver = chromeBrowser();
			break;
		case "FIREFOX":
			break;
		case "IE":
			break;
		case "CHROMEMOBILEWEB":
			driver = mobileEmulatorchromeBrowser();
			break;
		case "SAFARI":
			break;
		case "EDGE":
			break;
		}
		return driver;
	}

	public static WebDriver chromeBrowser() {
		ChromeOptions chromeOptions = new ChromeOptions();
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver(chromeOptions);
		driver.manage().window().maximize();
		return driver;
	}

	public static WebDriver mobileEmulatorchromeBrowser() {
		String MobileEmulator = System.getProperty("MobileEmulator");
		Map<String, String> mobileEmulation = new HashMap<>();
		if (MobileEmulator != null) {
			mobileEmulation.put("deviceName", MobileEmulator);
		} else {
			mobileEmulation.put("deviceName", "");
		}
		ChromeOptions chromeOptions = new ChromeOptions();
		WebDriverManager.chromedriver().setup();
		chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
		driver = new ChromeDriver(chromeOptions);
		driver.manage().window().maximize();
		return driver;
	}

}
