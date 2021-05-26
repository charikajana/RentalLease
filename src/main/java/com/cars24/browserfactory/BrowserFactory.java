package com.cars24.browserfactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserFactory {
	static WebDriver driver;

	public static WebDriver getbrowser(String BrowserName) {
		switch (BrowserName.toUpperCase()) {
		case "CHROME":			
			driver=chromeBrowser();
			break;
		case "FIREFOX":
			break;
		case "IE":
			break;
		case "CHROMEMOBILEWEB":
			break;
		case "SAFARI":
			break;
		case "EDGE":
			break;
		}
		return driver;
	}
	
	
	public static WebDriver chromeBrowser() {
		ChromeOptions chromeOptions=new ChromeOptions();
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver(chromeOptions);
		driver.manage().window().maximize();
		return driver;
	}

}
