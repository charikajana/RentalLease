package com.cars24.testrunnerfiles;

import org.testng.annotations.Test;

import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		plugin = { 
				"pretty",
				"com.cars24.testrunnerfiles.CustomPlugin",
				"rerun:target/rerun.txt",
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
				}, 
		glue = {
				"com.cars24.stepdefinations" 
				}, 
		features = { 
				"classpath:features" 
				}, 
		monochrome = true
		)

@Test
public class TestRunner extends CustomizeTestNGCucumberRunner {

}
