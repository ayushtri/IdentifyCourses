package com.identifycourses.stepdefinitions;

import com.identifycourses.base.DriverSetup;

import io.cucumber.java.en.Given;

public class BaseStep {
	@Given("I open the Coursera homepage")
	public void openCourseraHomepage() {
	    DriverSetup.navigateToApplication();
	}
}
