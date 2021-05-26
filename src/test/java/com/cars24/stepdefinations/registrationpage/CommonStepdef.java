package com.cars24.stepdefinations.registrationpage;

import com.cars24.applicationObjects.Cars24PageObjects;
import com.cars24.applicationconstants.ApplicationConstants;
import com.cars24.fileoperations.LoadProperties;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.*;

public class CommonStepdef extends Cars24PageObjects {
	Scenario scenario;
	public Cars24PageObjects cars24pageobjects;
	
	public CommonStepdef(Cars24PageObjects cars24pageobjects){
		this.cars24pageobjects=cars24pageobjects;
	}
	

	@Before
	public void before(Scenario scenario) {
		this.scenario = scenario;
	}

	@Given("User open the browser and enter the URL")
	public void user_open_the_browser_and_enter_the_url() {
		cars24pageobjects.webdrierinteractions.loadEnvironmentProperties("DEV");
		cars24pageobjects.webdrierinteractions.get(LoadProperties.DerivergetProperty(ApplicationConstants.URL));
		cars24pageobjects.webdrierinteractions.click("com.cars24.landingpage.closeOption");
	}

	@And("Close the browser")
	public void close_the_browser() {
		cars24pageobjects.webdrierinteractions.closeCurrentActiveBrowser();
	}

}
