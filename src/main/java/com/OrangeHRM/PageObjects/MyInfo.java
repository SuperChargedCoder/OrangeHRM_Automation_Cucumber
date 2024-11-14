package com.OrangeHRM.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.OrangeHRM.AbstractComponents.Reusable;

public class MyInfo extends Reusable {

	WebDriver driver;

	public MyInfo(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "input[name='firstName']")
	WebElement firstNameField;

	@FindBy(css = "input[name='middleName']")
	WebElement middleNameField;

	@FindBy(css = "input[name='lastName']")
	WebElement lastNameField;

	@FindBy(xpath = "//label[text()='Employee Id']/following::input[1]")
	public WebElement employeeIdField;

	@FindBy(xpath = "//label[text()='Date of Birth']/following::input[1]")
	public WebElement dateOfBirthField;

	@FindBy(xpath = "//label[text()='Nationality']/following::div[@class='oxd-select-wrapper'][1]")
	WebElement nationalityDropdown;

	@FindBy(xpath = "//label[text()='Marital Status']/following::div[@class='oxd-select-wrapper'][1]")
	WebElement maritalStatusDropdown;

	@FindBy(xpath = "//label[normalize-space()='Male']")
	WebElement maleRadioButton;

	@FindBy(xpath = "//label[normalize-space()='Female']")
	WebElement femaleRadioButton;

	@FindBy(xpath = "//div[@class='orangehrm-horizontal-padding orangehrm-vertical-padding']//button[@type='submit'][normalize-space()='Save']")
	public WebElement saveButton;

	@FindBy(css = "button[type='button'].oxd-button.oxd-button--medium.oxd-button--text")
	WebElement addAttachmentButton;

	public void setFirstName(String firstName) {
		firstNameField.sendKeys(firstName);
	}

	public WebElement UpdateEmpId(String id) {
		if (employeeIdField.isEnabled()) {
			employeeIdField.sendKeys(id);
		}
		return employeeIdField;
	}

	public WebElement UpdateDOB(String DOB) {
		if (dateOfBirthField.isEnabled()) {
			dateOfBirthField.sendKeys(DOB);
		}
		return dateOfBirthField;
	}

	public void selectNationality(String nationality) throws InterruptedException {
		char input = nationality.charAt(0);
		nationalityDropdown.click();
		Thread.sleep(5000);
		do {
			nationalityDropdown.sendKeys(Character.toString(input));
		} while (!nationalityDropdown.getText().equalsIgnoreCase(nationality));
	}

	public void selectMaritalStatus(String maritalStatus) {
		char input = maritalStatus.charAt(0);
		while (!maritalStatusDropdown.getText().equalsIgnoreCase(maritalStatus)) {
			maritalStatusDropdown.sendKeys(Character.toString(input));
		}
	}

	String selectedGender;

	public void selectGender(String gender) {
		if (gender.equalsIgnoreCase("Male")) {
			maleRadioButton.click();
			selectedGender = "Male";
		} else if (gender.equalsIgnoreCase("Female")) {
			femaleRadioButton.click();
			selectedGender = "Female";
		}
	}

	public boolean OnlyOneCheck() {
		if (selectedGender.equalsIgnoreCase("male")) {
			return femaleRadioButton.isSelected();
		} else if (selectedGender.equalsIgnoreCase("female")) {
			return maleRadioButton.isSelected();
		}
		return true;
		
		/*
		if (selectedGender.equalsIgnoreCase("male")) {
	        return !femaleRadioButton.isSelected() && maleRadioButton.isSelected();
	    } else if (selectedGender.equalsIgnoreCase("female")) {
	        return !maleRadioButton.isSelected() && femaleRadioButton.isSelected();
	    }
	    return false; // Return false if selectedGender is not "male" or "female"
		
		*/
	}

}
