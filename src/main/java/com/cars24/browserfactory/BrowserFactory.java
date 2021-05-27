package com.cars24.browserfactory;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;



import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserFactory {
	static WebDriver driver;
	static String FILE_SEPARATOR="\"";

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
		ChromeOptions chromeoptions=setChromeOptions();
		WebDriverManager.chromedriver().setup();        
		driver = new ChromeDriver(chromeoptions);
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
	
	public static ChromeOptions setChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        String downloadFilepath = System.getProperty("user.dir") + FILE_SEPARATOR + "downloads";
        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("download.prompt_for_download", false);
        chromePrefs.put("download.directory_upgrade", true);
        chromePrefs.put("profile.default_content_settings.popups", false);
        chromePrefs.put("download.default_directory", downloadFilepath);
        options.setExperimentalOption("prefs", chromePrefs);
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-popup-blocking");
        //options.addArguments("--start-maximized");
        options.addArguments("--window-size=1920,1080");
        options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        options.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
        return options;
    }

}
