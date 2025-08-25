package com.identifycourses.stepdefinitions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import com.identifycourses.models.FormData;
import com.identifycourses.pages.EnterprisePage;
import com.identifycourses.utils.ConfigReader;
import com.identifycourses.utils.ExcelUtil;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class EnterpriseFormValidationSteps {

    private static final Logger logger = LogManager.getLogger(EnterpriseFormValidationSteps.class);

    EnterprisePage enterprisePage;
    ExcelUtil excelUtil = new ExcelUtil(ConfigReader.getTestDataFile(), ConfigReader.getTestDataSheetNameForm());

    public EnterpriseFormValidationSteps() {
        this.enterprisePage = new EnterprisePage();
    }

    @When("user navigates to For Enterprise section")
    public void userNavigatesToForEnterprise() {
        enterprisePage.navigateToForEnterprise();
    }

    @When("user scrolls and clicks on the Campus product")
    public void userScrollsAndClicksCampus() {
        enterprisePage.navigateToCampusProduct();
    }

    @Then("user fills the form with invalid email from Excel sheet")
    public void userFillsFormWithInvalidEmail() {
        Object[][] testData = excelUtil.getSheetData();

        if (testData != null && testData.length > 0) {
            for (Object[] row : testData) {
                FormData formData = (FormData) row[0];
                enterprisePage.fillForm(formData);
                enterprisePage.getEmailErrorMessage();
                enterprisePage.clearFormFields();
            }
        } else {
            logger.warn("No data found in Excel sheet.");
        }
    }

    @Then("user retrieves and logs the error message for Email field")
    public void logEmailFieldError() {
    	String emailError = enterprisePage.getEmailErrorMessage();
        Assert.assertNotNull(emailError, "Email error message should be displayed.");
    }

    @Then("user leaves First Name field empty and submits the form")
    public void userLeavesFirstNameEmpty() {
        enterprisePage.fillFormWithMissingField("firstname");
    }

    @Then("user retrieves the error message for First Name field")
    public void logFirstNameError() {
    	String error = enterprisePage.getFirstNameErrorMessage();
        Assert.assertNotNull(error, "First Name error message should be displayed.");
    }

    @Then("user leaves Last Name field empty and submits the form")
    public void userLeavesLastNameEmpty() {
        enterprisePage.fillFormWithMissingField("lastname");
    }

    @Then("user retrieves the error message for Last Name field")
    public void logLastNameError() {
    	String error = enterprisePage.getLastNameErrorMessage();
        Assert.assertNotNull(error, "Last Name error message should be displayed.");
    }

    @Then("user fills the Phone Number field with invalid data")
    public void fillInvalidPhone() {
        enterprisePage.fillFormWithInvalidPhone();
    }

    @Then("user retrieves the error message for Phone Number field")
    public void logPhoneError() {
    	String error = enterprisePage.getPhoneErrorMessage();
        Assert.assertNotNull(error, "Phone number error message should be displayed.");
    }

    @Then("user does not select any Institution Type and submits the form")
    public void institutionTypeMissing() {
        enterprisePage.fillFormWithMissingField("institutionType");
    }

    @Then("user retrieves the error message for Institution Type dropdown")
    public void logInstitutionTypeError() {
    	String error = enterprisePage.getInstitutionTypeErrorMessage();
        Assert.assertNotNull(error, "Institution Type error message should be displayed.");
    }

    @Then("user leaves Institution Name field empty and submits the form")
    public void institutionNameMissing() {
        enterprisePage.fillFormWithMissingField("institutionName");
    }

    @Then("user retrieves the error message for Institution Name field")
    public void logInstitutionNameError() {
    	String error = enterprisePage.getInstitutionNameErrorMessage();
        Assert.assertNotNull(error, "Institution Name error message should be displayed.");
    }

    @Then("user does not select any Job Role and submits the form")
    public void jobRoleMissing() {
        enterprisePage.fillFormWithMissingField("jobRole");
    }

    @Then("user retrieves the error message for Job Role dropdown")
    public void logJobRoleError() {
    	String error = enterprisePage.getJobRoleErrorMessage();
        Assert.assertNotNull(error, "Job Role error message should be displayed.");
    }

    @Then("user does not select any Department and submits the form")
    public void departmentMissing() {
        enterprisePage.fillFormWithMissingField("department");
    }

    @Then("user retrieves the error message for Department dropdown")
    public void logDepartmentError() {
    	String error = enterprisePage.getDepartmentErrorMessage();
        Assert.assertNotNull(error, "Department error message should be displayed.");
    }

    @Then("user does not select any option in Needs dropdown and submits the form")
    public void needsMissing() {
        enterprisePage.fillFormWithMissingField("needs");
    }

    @Then("user retrieves the error message for Needs dropdown")
    public void logNeedsError() {
    	String error = enterprisePage.getNeedsErrorMessage();
        Assert.assertNotNull(error, "Needs error message should be displayed.");
    }

    @Then("user selects Country but does not select any State and submits the form")
    public void stateMissing() {
        enterprisePage.fillFormWithMissingField("state");
    }

    @Then("user retrieves the error message for State dropdown")
    public void logStateError() {
    	String error = enterprisePage.getStateErrorMessage();
        Assert.assertNotNull(error, "State error message should be displayed.");
    }
}
