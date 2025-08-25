package com.identifycourses.pages;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import com.identifycourses.models.FormData;
import com.identifycourses.utils.ConfigReader;
import com.identifycourses.utils.JsonUtil;

public class EnterprisePage extends BasePage {

	private static final Logger logger = LogManager.getLogger(EnterprisePage.class);

	private JavascriptExecutor js;

	@FindBy(xpath = "//h3[text()='Campus']")
	private WebElement campusElement;

	@FindBy(xpath = "//span[contains(text(), 'Learn more')]")
	private List<WebElement> learnMoreList;

	@FindBy(id = "FirstName")
	private WebElement fnameEle;

	@FindBy(id = "LastName")
	private WebElement lnameEle;

	@FindBy(id = "Email")
	private WebElement emailField;

	@FindBy(id = "Phone")
	private WebElement phoneField;

	@FindBy(id = "Institution_Type__c")
	private WebElement institutionDropdown;

	@FindBy(id = "Company")
	private WebElement institutionNameEle;

	@FindBy(id = "Title")
	private WebElement jobRoleDropdown;

	@FindBy(id = "Department")
	private WebElement departmentDropdown;

	@FindBy(id = "What_the_lead_asked_for_on_the_website__c")
	private WebElement needsDropdown;

	@FindBy(id = "Country")
	private WebElement countryDropdown;

	@FindBy(id = "State")
	private WebElement stateDropdown;

	@FindBy(className = "mktoButton")
	private WebElement submitBtn;

	@FindBy(xpath = "//div[@id = 'ValidMsgEmail' and @class = 'mktoErrorMsg']")
	private WebElement invalidEmail;

	@FindBy(xpath = "//div[@id='ValidMsgFirstName' and contains(@class, 'mktoErrorMsg')]")
	private WebElement invalidFirstName;

	@FindBy(xpath = "//div[@id='ValidMsgLastName' and contains(@class, 'mktoErrorMsg')]")
	private WebElement invalidLastName;

	@FindBy(xpath = "//div[@id='ValidMsgPhone' and contains(@class, 'mktoErrorMsg')]")
	private WebElement invalidPhone;

	@FindBy(xpath = "//div[@id='ValidMsgInstitution_Type__c' and contains(@class, 'mktoErrorMsg')]")
	private WebElement invalidInstitutionType;

	@FindBy(xpath = "//div[@id='ValidMsgCompany' and contains(@class, 'mktoErrorMsg')]")
	private WebElement invalidInstitutionName;

	@FindBy(xpath = "//div[@id='ValidMsgTitle' and contains(@class, 'mktoErrorMsg')]")
	private WebElement invalidJobRole;

	@FindBy(xpath = "//div[@id='ValidMsgDepartment' and contains(@class, 'mktoErrorMsg')]")
	private WebElement invalidDepartment;

	@FindBy(xpath = "//div[@id='ValidMsgWhat_the_lead_asked_for_on_the_website__c' and contains(@class, 'mktoErrorMsg')]")
	private WebElement invalidNeeds;

	@FindBy(xpath = "//div[@id='ValidMsgCountry' and contains(@class, 'mktoErrorMsg')]")
	private WebElement invalidCountry;

	@FindBy(xpath = "//div[@id='ValidMsgState' and contains(@class, 'mktoErrorMsg')]")
	private WebElement invalidState;

	JsonUtil jsonUtil;
	FormData validData;

	public EnterprisePage() {
		super();
		this.js = (JavascriptExecutor) driver;
		this.jsonUtil = new JsonUtil(ConfigReader.getTestDataFileJson());
		this.validData = jsonUtil.readFormData();
	}

	public void navigateToForEnterprise() {
		try {
			String enterpriseUrl = driver.getCurrentUrl() + "enterprise";
			driver.navigate().to(enterpriseUrl);
		} catch (Exception e) {
			logger.error("Cannot navigate to Enterprise page", e);
		}
	}

	public void navigateToCampusProduct() {
		try {
			wait.until(ExpectedConditions.visibilityOf(campusElement));
			String scrollScript = "arguments[0].scrollIntoView({block: 'center'})";

			logger.info("Scrolling to Campus Product element");

			js.executeScript(scrollScript, campusElement);

			learnMoreList.get(1).click();
		} catch (Exception e) {
			logger.error("Cannot navigate to Campus Product Page", e);
		}
	}

	public void fillForm(FormData formData) {
		try {
			String scrollScript = "arguments[0].scrollIntoView({block: 'center'})";

			js.executeScript(scrollScript, emailField);

			logger.info("Filling form");

			fnameEle.sendKeys(formData.getFirstName());
			lnameEle.sendKeys(formData.getLastName());
			emailField.sendKeys(formData.getEmail());
			phoneField.sendKeys(formData.getPhoneNumber());

			selectDropdowns(institutionDropdown, formData.getInstitutionType());

			institutionNameEle.sendKeys(formData.getInstitutionName());

			selectDropdowns(jobRoleDropdown, formData.getJobRole());
			selectDropdowns(departmentDropdown, formData.getDepartment());
			selectDropdowns(needsDropdown, formData.getNeeds());

			selectDropdowns(countryDropdown, formData.getCountry());

			wait.until(ExpectedConditions.elementToBeClickable(stateDropdown));
			selectDropdowns(stateDropdown, formData.getState());

			logger.info("Submitting form");
			submitBtn.click();

		} catch (Exception e) {
			logger.error("Error in interating with form", e);
		}
	}

	private void selectDropdowns(WebElement element, String text) {
		Select select = new Select(element);
		select.selectByVisibleText(text);
	}

	public void clearFormFields() {
		try {
			fnameEle.clear();
			lnameEle.clear();
			emailField.clear();
			phoneField.clear();
			institutionNameEle.clear();
		} catch (Exception e) {
			logger.warn("Unable to clear some form fields", e);
		}
	}

	// Generic method to fill form with a missing field by clearing it and
	// submitting the form
	public void fillFormWithMissingField(String fieldName) {
		try {
			switch (fieldName.toLowerCase()) {
			case "firstname":
				validData.setFirstName("");
				break;
			case "lastname":
				validData.setLastName("");
				break;
			case "email":
				validData.setEmail("");
				break;
			case "phonenumber":
			case "phone":
				validData.setPhoneNumber("");
				break;
			case "institutiontype":
				validData.setInstitutionType("Select...");
				break;
			case "institutionname":
				validData.setInstitutionName("");
				break;
			case "jobrole":
				validData.setJobRole("Select...");
				break;
			case "department":
				validData.setDepartment("Select...");
				break;
			case "needs":
				validData.setNeeds("Select...");
				break;
			case "country":
				validData.setCountry("India");
				break;
			case "state":
				validData.setState("State");
				break;
			default:
				logger.warn("Unknown field name '{}' provided to fillFormWithMissingField", fieldName);
				break;
			}

			fillForm(validData);
		} catch (Exception e) {
			logger.error("Error filling form with missing field: " + fieldName, e);
		}
	}

	public void fillFormWithInvalidPhone() {
		try {
			validData.setPhoneNumber("invalid-number");
			fillForm(validData);
		} catch (Exception e) {
			logger.error("Error filling form with invalid phone", e);
		}
	}

	private String extractErrorMessage(WebElement errorElement) {
		try {
			wait.until(ExpectedConditions.visibilityOf(errorElement));
			js.executeScript("arguments[0].scrollIntoView({block: 'center'});", errorElement);
			return errorElement.getText();
		} catch (Exception e) {
			logger.warn("Error message not found or not visible", e);
			return null;
		}
	}

	public String getEmailErrorMessage() {
		String message = extractErrorMessage(invalidEmail);
		logger.info("Email Error Message: {}", message);
		return message;
	}

	public String getFirstNameErrorMessage() {
		String message = extractErrorMessage(invalidFirstName);
		logger.info("First Name Error Message: {}", message);
		return message;
	}

	public String getLastNameErrorMessage() {
		String message = extractErrorMessage(invalidLastName);
		logger.info("Last Name Error Message: {}", message);
		return message;
	}

	public String getPhoneErrorMessage() {
		String message = extractErrorMessage(invalidPhone);
		logger.info("Phone Error Message: {}", message);
		return message;
	}

	public String getInstitutionTypeErrorMessage() {
		String message = extractErrorMessage(invalidInstitutionType);
		logger.info("Institution Type Error Message: {}", message);
		return message;
	}

	public String getInstitutionNameErrorMessage() {
		String message = extractErrorMessage(invalidInstitutionName);
		logger.info("Institution Name Error Message: {}", message);
		return message;
	}

	public String getJobRoleErrorMessage() {
		String message = extractErrorMessage(invalidJobRole);
		logger.info("Job Role Error Message: {}", message);
		return message;
	}

	public String getDepartmentErrorMessage() {
		String message = extractErrorMessage(invalidDepartment);
		logger.info("Department Error Message: {}", message);
		return message;
	}

	public String getNeedsErrorMessage() {
		String message = extractErrorMessage(invalidNeeds);
		logger.info("Needs Error Message: {}", message);
		return message;
	}

	public String getCountryErrorMessage() {
		String message = extractErrorMessage(invalidCountry);
		logger.info("Country Error Message: {}", message);
		return message;
	}

	public String getStateErrorMessage() {
		String message = extractErrorMessage(invalidState);
		logger.info("State Error Message: {}", message);
		return message;
	}

}
