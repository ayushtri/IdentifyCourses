package com.identifycourses.stepdefinitions;

import java.util.List;

import org.testng.Assert;

import com.identifycourses.base.DriverSetup;
import com.identifycourses.models.SearchResults;
import com.identifycourses.pages.HomePage;
import com.identifycourses.pages.SearchPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SearchWebDevCoursesSteps {
	HomePage homePage;
	SearchPage searchPage;
	
	public SearchWebDevCoursesSteps() {
		this.searchPage = new SearchPage();
		this.homePage = new HomePage();
	}
	
	@Given("I open the Coursera homepage")
	public void openCourseraHomepage() {
	    DriverSetup.navigateToApplication();
	}

	@When("I search for courses of {string}")
	public void searchFor(String course) {
	    homePage.searchCourse(course);
	}

	@When("I apply the filter for Beginner level")
	public void applyFilterForBeginnerLevel() {
	    Assert.assertTrue(searchPage.filterBeginnerLevel(), "Couldn't apply filter Beginner level");
	}

	@When("I apply the filter for English language")
	public void applyFilterEnglishLanguage() {
	    Assert.assertTrue(searchPage.filterCourseLanguage(), "Couldn't apply filter English language");
	}

	@Then("I should see the first {int} courses displayed and display their course names, total learning hours and ratings")
	public void coursesDetailsDisplay(int n) {
		List<SearchResults> courseList = searchPage.extractCourseDetails(n);
		Assert.assertNotNull(courseList, "Course list should not be null");
		Assert.assertFalse(courseList.isEmpty(), "Course list should not be empty");
	}
}
