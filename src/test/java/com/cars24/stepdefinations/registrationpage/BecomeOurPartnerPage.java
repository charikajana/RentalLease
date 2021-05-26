package com.cars24.stepdefinations.registrationpage;

import org.testng.Assert;

import com.cars24.applicationObjects.Cars24PageObjects;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

public class BecomeOurPartnerPage extends Cars24PageObjects {
	Scenario scenario;
	public Cars24PageObjects cars24pageobjects;
	
	public BecomeOurPartnerPage(Cars24PageObjects cars24pageobjects){
		this.cars24pageobjects=cars24pageobjects;
	}
	@Before
	public void before(Scenario scenario) {
		this.scenario = scenario;
	}
	
	@And("click on BecomeOurPartner Link")
	public void click_on_BecomeOurPartner_link() {
		cars24pageobjects.homepage.BecomeOurpartner();
		cars24pageobjects.webdrierinteractions.setScreenshot(scenario,scenario.getUri().toString());
		
	}	
	@Then("Verify the BecomeourPartner page title")
	public void verify_the_BecomeourPartner_page_title() {
		String title = cars24pageobjects.webdrierinteractions.capturePageTitle();
		System.out.println("Title of the Page" + title);
		Assert.assertEquals(title,
				"Register today to transform the Used Car Industry!");
	}


}
