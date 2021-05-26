package com.cars24.appiumdependencies;

import java.io.IOException;

import org.openqa.selenium.remote.DesiredCapabilities;



public class LaunchGridAndNodeServer {
	static DesiredCapabilities capabilites;
	protected static String udid;
	protected static int portNumber;
	public static String Appium_NodePath;
	public static String Appium_AppiumJspath;
	//private static Logger logger = Logger.getLogger("LaunchGridAndNodeServer");

	/*
	 * public static void launchSeleniumGrid(String HubAddress) { try {
	 * GridHubConfiguration config = new GridHubConfiguration();
	 * config.setHost(HubAddress); config.setTimeout(30000000);
	 * config.setPort(4444); Hub hub = new Hub(config); hub.start();
	 * System.err.println(hub.getRegistrationURL().toString()); } catch (Exception
	 * e) {
	 * 
	 * }
	 * 
	 * }
	 */

	public static void launchAppiumServer(String HubAddress) {
		if (System.getProperty("os.name").contains("Windows")) {
			AppiumJsonUtil.createJsonFile();
			udid = DeviceDetailUtil.deviceId;
			portNumber = DeviceDetailUtil.portNumber;
			String path = System.getProperty("user.dir");
			String appiumserverpath = "cmd /c start cmd.exe /K \"cd " + path
					+ "\\src\\main\\resources\\browsers\\MobileConfigurationFiles\\Appium" + "&& node.exe " + path
					+ "\\src\\main\\resources\\browsers\\MobileConfigurationFiles\\Appium\\node_modules\\appium\\bin\\appium.js --address "
					+ HubAddress + " --port " + portNumber + " -bp 2252 --udid " + udid + " --nodeconfig " + path
					+ "\\src\\main\\resources\\browsers\\MobileConfigurationFiles\\devicejsonfiles\\" + udid + ".json";

			try {
				Runtime.getRuntime().exec(appiumserverpath);
				Thread.sleep(10000);
			} catch (Exception e) {
			}
		} else if (System.getProperty("os.name").contains("Mac")) {

		}

	}

	public static void stopNodeserver(int portnumber) {
		try {
			Runtime.getRuntime()
					.exec("cmd /c echo off & FOR /F \"usebackq tokens=5\" %a in (`netstat -nao ^| findstr /R /C:\""
							+ String.valueOf(portnumber)
							+ "\"`) do (FOR /F \"usebackq\" %b in (`TASKLIST /FI \"PID eq %a\" ^| findstr /I node.exe`) do taskkill /F /PID %a)");
		} catch (IOException e) {
			//logger.info("Given port number is not in use");
		}
	}

}
