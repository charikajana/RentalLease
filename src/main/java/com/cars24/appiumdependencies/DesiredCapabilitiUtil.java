package com.cars24.appiumdependencies;

import java.io.File;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.cars24.applicationconstants.ApplicationConstants;
import com.cars24.fileoperations.LoadProperties;

import io.appium.java_client.AppiumDriver;

public class DesiredCapabilitiUtil {
	protected static String udid;
	public static DesiredCapabilities capabilities = null;
	public static AppiumDriver<WebElement> driver;
	public static String apppackage;
	public static String appactivity;
	public static String app;
	public static String HubAddress = "127.0.0.1";
	public static String portNumber;

	public static DesiredCapabilities getDesiredCapabilities() {
		AppiumServer.startAppiumServer(LoadProperties.DerivergetProperty(ApplicationConstants.APPIUMSERVERPATH));

		try {
			capabilities = new DesiredCapabilities();
			capabilities.setCapability("automationName", "Selendroid");
			capabilities.setCapability("deviceName", DeviceDetailUtil.getdeviceUdid());
			capabilities.setCapability("platformVersion", DeviceDetailUtil.getDeviceVersion());
			capabilities.setCapability("platformName", DeviceDetailUtil.getDevicePlatformName().toUpperCase());
			capabilities.setCapability("app", "chrome");
			capabilities.setCapability("appPackage",
					LoadProperties.DerivergetProperty(ApplicationConstants.APP_PACKAGE));
			capabilities.setCapability("appActivity",
					LoadProperties.DerivergetProperty(ApplicationConstants.APP_ACTIVITY));
			capabilities.setCapability("app",
					new File(System.getProperty("user.dir") + "\\src\\main\\resources\\AppResuorces\\"
							+ (LoadProperties.DerivergetProperty(ApplicationConstants.APKFILENAME) + ".apk")));
			capabilities.setCapability("newCommandTimeout", 500000);
			capabilities.setCapability("autoAcceptAlerts", true);
			capabilities.setCapability("autoDismissAlerts", true);
			return capabilities;
		} catch (Exception e) {

		}
		return capabilities;
	}

}
