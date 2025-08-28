package com.identifycourses.pages;


import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.identifycourses.models.LanguageDetails;
import com.identifycourses.models.LanguageLevels;


public class LanguageLearningPage extends BasePage {
	
	private static final Logger logger = LogManager.getLogger(LanguageLearningPage.class);
	
	private JavascriptExecutor js;
	
	@FindBy(xpath = "//*[text()='Explore']")
	private WebElement exploreBtn;
	
	@FindBy(linkText = "Language Learning")
	private WebElement languageLearning;
	
	@FindBy(xpath = "//div[@data-testid='search-filter-group-Language']//span[contains(text(), 'Show')]")
	private WebElement langaugesShowMore;
	
	@FindBy(xpath = "//div[contains(@data-testid, 'language')]")
	private List<WebElement> allLanguages;
	
	@FindBy(xpath = "//div[contains(@data-testid, 'productDifficultyLevel')]")
	private List<WebElement> allLanguagesLevels;
	
	public LanguageLearningPage() {
		super();
		this.js = (JavascriptExecutor) driver;
	}
	
	public void navigateToLanguageLearning() {
		try {
			Actions action = new Actions(driver);
			action.moveToElement(exploreBtn).perform();
			
			wait.until(ExpectedConditions.visibilityOf(languageLearning)).click(); 
			
		} catch(Exception e) {
			logger.error("Error occurred while navigating to Language Learning Page", e);
		}
	}
	
	public List<LanguageDetails> fetchAllLanguagesAndCount() {
		List<LanguageDetails> allLanguageDetails = new ArrayList<>();
		LanguageDetails languageDetails = null;
		
		try {
			String scrollScript = "arguments[0].scrollIntoView({block: 'center'});";
			logger.info("Scrolling to Languages element");
			js.executeScript(scrollScript, langaugesShowMore);
			
			Actions action = new Actions(driver);
			action.moveToElement(langaugesShowMore).click().perform();
			
			wait.until(ExpectedConditions.visibilityOfAllElements(allLanguages));
			
			for(WebElement langElement : allLanguages) {
				String text = langElement.getText(); 
				
				if (text != null && !text.isEmpty()) {
                    String language = text.replaceAll("[^a-zA-Z]+", "").trim(); // "English"
                    String countStr = text.replaceAll("[^0-9,]", ""); // "13,100"
                    int count = Integer.parseInt(countStr.replace(",", "")); // 13100
                    languageDetails = new LanguageDetails(language, count);
                }
				
				allLanguageDetails.add(languageDetails);
			}
			
		} catch(Exception e) {
			logger.error("Error in fetching all languages", e);
		}
		return allLanguageDetails;
	}
	
	public List<LanguageLevels> fetchLanguageLevelsAndCount() {
		List<LanguageLevels> allLanguageLevels = new ArrayList<>();
		LanguageLevels languageLevels = null;
		try {
			String scrollScript = "arguments[0].scrollIntoView({block: 'center'});";
			logger.info("Scrolling to Languages element");
			js.executeScript(scrollScript, allLanguagesLevels.get(0));
			
			for(WebElement langElement : allLanguagesLevels) {
				String text = langElement.getText(); 
				if (text != null && !text.isEmpty()) {
                    String languageLevel = text.replaceAll("[^a-zA-Z]+", "").trim(); 
                    String countStr = text.replaceAll("[^0-9,]", ""); 
                    int count = Integer.parseInt(countStr.replace(",", "")); 
                    languageLevels = new LanguageLevels(languageLevel, count);
                }
				
				allLanguageLevels.add(languageLevels);
			}
			
		} catch(Exception e) {
			logger.error("Error in fetching all languages levels", e);
		}
		return allLanguageLevels;
	}
}
