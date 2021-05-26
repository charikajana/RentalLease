package com.cars24.stepdefinations.registrationpage;

import org.testng.Assert;

import com.cars24.applicationObjects.Cars24PageObjects;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.*;

public class RCStatusPageStepdef extends Cars24PageObjects {
	Scenario scenario;
	public Cars24PageObjects cars24pageobjects;
	
	public RCStatusPageStepdef(Cars24PageObjects cars24pageobjects){
		this.cars24pageobjects=cars24pageobjects;
	}

	@Before
	public void before(Scenario scenario) {
		this.scenario = scenario;
	}

	@And("click on RCStatus Link")
	public void click_on_RCStatus_link() {
		cars24pageobjects.homepage.RCTransferstatus();
		cars24pageobjects.webdrierinteractions.setScreenshot(scenario,this.getClass().getName());

	}

	@Then("Verify the RC page title")
	public void verify_the_RC_page_title() {
		String title = cars24pageobjects.webdrierinteractions.capturePageTitle();
		System.out.println("Title of the Page" + title);
		Assert.assertEquals(title,
				"CARS24 - Sell Your Used Car & Get Paid Instantly | Get Free Car Valuation in India");
	}

}
