package com.identifycourses.stepdefinitions;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.Reporter;

import com.identifycourses.base.DriverSetup;
import com.identifycourses.utils.ScreenshotUtil;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;

public class Hooks {
	
	private static final Logger logger = LogManager.getLogger(Hooks.class);
	
	@Before
	public void setup(Scenario scenario) {
	    ITestContext context = Reporter.getCurrentTestResult().getTestContext();
	    String os = (String) context.getAttribute("os");
	    String browser = (String) context.getAttribute("browser");
	    DriverSetup.initializeDriver(os, browser);
	}
	
	@After
	public void tearDown(Scenario scenario) {
		if(DriverSetup.getDriver() != null) {
			String screenshotPath = ScreenshotUtil.getScreenshot(DriverSetup.getDriver(), scenario.getName());
			
			File screenshotFile = new File(screenshotPath);
            if (screenshotFile.exists()) {
            	try(FileInputStream fis = new FileInputStream(screenshotFile)) {
            		logger.info("Attaching screenshot to allure report");
            		Allure.addAttachment("Screenshot - " + scenario.getName(), fis);
            	} catch(IOException e) {
            		logger.error("Error in attaching screenshots to allure report", e);
            	}
                
            }
		}
		
        DriverSetup.tearDown();
	}

}
