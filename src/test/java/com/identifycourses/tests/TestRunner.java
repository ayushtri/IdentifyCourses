package com.identifycourses.tests;

import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.identifycourses.utils.AllureReportOpener;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com.identifycourses.stepdefinitions"}, 
        tags = "@Smoke or @Fieldlevel",
        plugin = {
                "pretty",
                "html:test-output/cucumber-reports/cucumber.html",
                "json:test-output/cucumber-reports/cucumber.json",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
        }
)
public class TestRunner extends AbstractTestNGCucumberTests {
	
	@BeforeMethod
	@Parameters({"os", "browser"})
	public void setUp(String os, String browser, ITestContext context) {
	    context.setAttribute("os", os);
	    context.setAttribute("browser", browser);
	}

	
//	@BeforeSuite
//	public void beforeSuite() {
//	    AllureReportOpener.cleanAllureResults();
//	}
	
//	@AfterSuite
//	public void afterSuite() {
//	    AllureReportOpener.openAllureReport();
//	}
	 
}
