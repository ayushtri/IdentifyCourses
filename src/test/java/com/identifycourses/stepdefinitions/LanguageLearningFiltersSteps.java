package com.identifycourses.stepdefinitions;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import com.identifycourses.models.LanguageDetails;
import com.identifycourses.models.LanguageLevels;
import com.identifycourses.pages.LanguageLearningPage;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LanguageLearningFiltersSteps {
	private static final Logger logger = LogManager.getLogger(LanguageLearningFiltersSteps.class);

	LanguageLearningPage languageLearningPage;
	List<LanguageDetails> allLanguageDetails = null;
	List<LanguageLevels> allLanguageLevels = null;
	
	
	public LanguageLearningFiltersSteps() {
		this.languageLearningPage = new LanguageLearningPage();
	}
	
	@When("I go to Language Learning")
	public void goTo() {
	    languageLearningPage.navigateToLanguageLearning();
	}
	
	@When("I click on first popular topic")
	public void clickFirstPopularTopic() {
		languageLearningPage.clickOnFirstPopularTopic();
	}

	@When("I extract the list of all available languages with their respective counts")
	public void extractLanguagesWithCounts() {
		allLanguageDetails = languageLearningPage.fetchAllLanguagesAndCount();
		Assert.assertNotNull(allLanguageDetails, "Language details list should not be null");
	    Assert.assertFalse(allLanguageDetails.isEmpty(), "Language details list should not be empty");
	}

	@When("I extract all learning levels with their respective counts")
	public void extractLanguageLevelsCounts() {
		allLanguageLevels = languageLearningPage.fetchLanguageLevelsAndCount();
		Assert.assertNotNull(allLanguageLevels, "Language levels list should not be null");
	    Assert.assertFalse(allLanguageLevels.isEmpty(), "Language levels list should not be empty");
	}

	@Then("I display the languages and levels with their total counts")
	public void i_display_the_languages_and_levels_with_their_total_counts() {
	    allLanguageDetails.forEach(n -> {
	    	logger.info(n);
	    	Assert.assertNotNull(n.getLanguage(), "Language should not be null");
	        Assert.assertTrue(n.getCount() > 0, "Language count should be greater than zero");
	    });
	    
	    allLanguageLevels.forEach(n -> {
	    	logger.info(n);
	    	Assert.assertNotNull(n.getLanguageLevel(), "Language level should not be null");
	        Assert.assertTrue(n.getCount() > 0, "Language level count should be greater than zero");
	    });
	}

}
