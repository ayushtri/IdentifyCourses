package com.identifycourses.pages;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.identifycourses.models.SearchResults;

public class SearchPage extends BasePage {
	
	private static final Logger logger = LogManager.getLogger(SearchPage.class);
	
	private JavascriptExecutor js;
	
	@FindBy(xpath = "//div[@class='filterContent']//span[text()='Beginner']")
	private WebElement courseLevel;
	
	@FindBy(xpath = "//*[contains(@data-testid, 'language:English')]//label")
	private WebElement courseLanguage;
	
	@FindBy(xpath = "//div[contains(@id, 'searchResults')]//*[@data-testid='product-card-cds']")
	private List<WebElement> searchCards;
	
	@FindBy(xpath = "//button//span[text()='Language: English']")
	private WebElement englishLanguageBtn;
	
	@FindBy(xpath = "//button//span[text()='Beginner']")
	private WebElement beginnerLevelBtn;
	
	public SearchPage() {
		super();
		this.js = (JavascriptExecutor) driver;
	}
	
	public boolean filterBeginnerLevel() {
		try {
			wait.until(ExpectedConditions.visibilityOf(courseLevel));
			String scrollScript = "arguments[0].scrollIntoView({block: 'center'});";
			
			logger.info("Scrolling to beginner level element");
			
			js.executeScript(scrollScript, courseLevel);
			
			wait.until(ExpectedConditions.elementToBeClickable(courseLevel));
			logger.info("Clicking beginner level element");
			courseLevel.click();
			
			logger.info("Clicked beginner level element");
			
			return wait.until(ExpectedConditions.visibilityOf(beginnerLevelBtn)).isDisplayed();
		} catch(Exception e) {
			logger.error("Error occured while scrolling and clicking beginner level", e);
		}
		return false;
	}
	
	public boolean filterCourseLanguage() {
		try {
			wait.until(ExpectedConditions.visibilityOf(courseLanguage));
			String scrollScript = "arguments[0].scrollIntoView({block: 'center'});";
			
			logger.info("Scrolling to English language element");
			
			js.executeScript(scrollScript, courseLanguage);
			
			wait.until(ExpectedConditions.elementToBeClickable(courseLanguage));
			logger.info("Clicking English language element");
			courseLanguage.click();
			
			logger.info("Clicked English language element");
			
			return wait.until(ExpectedConditions.visibilityOf(englishLanguageBtn)).isDisplayed();
		} catch(Exception e) {
			logger.error("Error occured while scrolling and clicking English language", e);
		}
		return false;
	}
	
	public List<SearchResults> extractCourseDetails(int n) {
		List<SearchResults> courseDetailsList = new ArrayList<>();
		try {
			for(int i = 0; i < Math.min(n, searchCards.size()); i++) {
				WebElement card = searchCards.get(i);
				
				WebElement titleElement = card.findElement(By.cssSelector("h3"));
	            String courseName = titleElement.getText();
	            
	            WebElement ratingElement = card.findElement(By.xpath("//*[@aria-label='Rating']//span"));
	            String rating = ratingElement.getText();
	            
	            WebElement durationElement = card.findElement(By.xpath("//p[contains(text(),'Course')]"));
	            String duration = durationElement.getText().split("\\·")[n].trim();
	            
	            SearchResults courseResult = new SearchResults(courseName, rating, duration);
	            courseDetailsList.add(courseResult);
	    
	            logger.info("Course Name - " + courseResult.getCourseName());
				logger.info("Rating - " + courseResult.getRating());
				logger.info("Duration - " + courseResult.getDuration());
			}
		} catch(Exception e) {
			logger.error("Error occurred while extracting course details", e);
		}
		return courseDetailsList;
	}
}
