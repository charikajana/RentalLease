package com.cars24.appiumdependencies;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class AppiumJsonUtil {
	public static String deviceVersion;
	public static String deviceapiLevel;
	public static String devicePlatform;
	public static String deviceId;
	public static int portNumber;
	public static String HUB_address;

	public static void getAllDetailsOfDevice() {
		try {
			deviceId = DeviceDetailUtil.getdeviceUdid();
			deviceVersion = DeviceDetailUtil.getDeviceVersion();
			devicePlatform = DeviceDetailUtil.getDevicePlatformName();
			portNumber = DeviceDetailUtil.getportNumber();
			deviceapiLevel = DeviceDetailUtil.getDeviceAPILevel();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public static File createJsonFile() {

		getAllDetailsOfDevice();
		String path = "src/main/resources/browsers/MobileConfigurationFiles/devicejsonfiles";
		File filename = new File(
				"src/main/resources/browsers/MobileConfigurationFiles/devicejsonfiles/" + deviceId + ".json");
		try {
			filename.createNewFile();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		File root = new File(path);
		File[] list = root.listFiles();
		if (list != null) {
			for (File f : list) {
				if (f.getName().contains(deviceId)) {
					JSONObject obj = new JSONObject();
					JSONArray jsonlist = new JSONArray();
					JSONObject info = new JSONObject();
					info.put("browserName", "");
					info.put("version", deviceVersion);
					info.put("platform", devicePlatform.toUpperCase());
					info.put("deviceName", deviceId);
					jsonlist.add(info);
					obj.put("capabilities", jsonlist);
					JSONObject secondinfo = new JSONObject();
					secondinfo.put("nodeTimeout", 120);
					secondinfo.put("port", portNumber);
					secondinfo.put("hubPort", 4444);
					secondinfo.put("proxy", "org.openqa.grid.selenium.proxy.DefaultRemoteProxy");
					secondinfo.put("hubHost", "127.0.0.1");
					secondinfo.put("nodePolling", 2000);
					secondinfo.put("registerCycle", 10000);
					secondinfo.put("register", true);
					secondinfo.put("cleanUpCycle", 2000);
					secondinfo.put("timeout", 30000);
					secondinfo.put("maxSession", 10);
					obj.put("configuration", secondinfo);
					try {
						FileWriter file = new FileWriter(f);
						file.write(obj.toJSONString());
						file.flush();
						file.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return root;

	}
}
