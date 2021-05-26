package com.cars24.applicationObjects;

import com.cars24.homepageObjects.HomePageReusable;
import com.cars24.webdriverinteractions.WebDriverInteractions;


public class Cars24PageObjects {
	public  WebDriverInteractions webdrierinteractions;
	public  HomePageReusable homepage;
	
	
	public Cars24PageObjects(){
		webdrierinteractions = new WebDriverInteractions();
		homepage = new HomePageReusable(webdrierinteractions);
	}
}
