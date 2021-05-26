package com.cars24.appiumdependencies;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class DeviceDetailUtil {
	public static String deviceVersion;
	public static String deviceapiLevel;
	public static String devicePlatform;
	public static String deviceId;
	public static int portNumber=0;

	public static String getDeviceVersion() {
		try {
			ProcessBuilder ps = new ProcessBuilder("adb", "shell", "getprop", "ro.build.version.release");
			ps.redirectErrorStream(true);
			Process p = ps.start();
			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line;
			while ((line = br.readLine()) != null) {
				deviceVersion = line;
				break;
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Error");
		}
		return deviceVersion;
	}

	public static String getDeviceAPILevel() throws AppiumNotSupportedException, NumberFormatException, IOException {

			ProcessBuilder ps = new ProcessBuilder("adb", "shell", "getprop", "ro.build.version.sdk");
			ps.redirectErrorStream(true);
			Process p = ps.start();
			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line;
			while ((line = br.readLine()) != null) {
				deviceapiLevel = line;
				if(Double.parseDouble(deviceapiLevel)<17)
				{
					throw new AppiumNotSupportedException("Appium not Supported for this Android SDK Level:  "+deviceapiLevel);
				}else
				{
					break;
				}
				
			}

		return deviceapiLevel;
	}

	public static String getDevicePlatformName() {

		try {
			ProcessBuilder ps = new ProcessBuilder("adb", "shell", "getprop", "ro.com.google.clientidbase");
			ps.redirectErrorStream(true);
			Process p = ps.start();
			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line;
			while ((line = br.readLine()) != null) {
				devicePlatform = line;
				break;
			}
			br.close();

		} catch (Exception e) {
			System.err.print("Error");
		}
		devicePlatform=devicePlatform.split("-")[0];
		return devicePlatform;
	}

	public static String getdeviceUdid() {

		try {
			ProcessBuilder ps = new ProcessBuilder("adb", "devices");
			ps.redirectErrorStream(true);
			Process pr = ps.start();
			BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
			String line;
			int count = 0;
			while ((line = in.readLine()) != null) {
				if (count == 0) {
					count = 1;
				} else if (count == 1) {
					deviceId = line.split("\t")[0];
					break;
				}
			}
			in.close();
			return deviceId;
		} catch (Exception e) {
			return null;
		}
	}
	public static int getportNumber()
	{
		int randomInt=0;
		int portnumber=4700;
		Random randomGenerator = new Random();
	    for (int i = 1; i <= 1; ++i){
	    randomInt = randomGenerator.nextInt(99);
	    if(randomInt>10)
	    {
	    	portNumber=portnumber+randomInt;
	    }else
	    {
	    	portNumber=portnumber+35;
	    }
		
	}
		return portNumber;
	}
}
