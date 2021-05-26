package com.cars24.homepageObjects;

import com.cars24.webdriverinteractions.WebDriverInteractions;

public class HomePageReusable {
	private WebDriverInteractions webDriverInteractions;

	public HomePageReusable(WebDriverInteractions webDriverInteractions) {
		this.webDriverInteractions = webDriverInteractions;
	}

	public void RCTransferstatus() {
		webDriverInteractions.click("com.cars24.RegistrationPage.RCStatus");
	}

	public void BecomeOurpartner() {
		webDriverInteractions.click("com.cars24.becomeOurPartner.becomourPartnerLink");
	}

}
