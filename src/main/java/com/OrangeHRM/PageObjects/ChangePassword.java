package com.OrangeHRM.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.OrangeHRM.AbstractComponents.Reusable;

public class ChangePassword extends Reusable {

	WebDriver driver;

	public ChangePassword (WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//label[text()='Current Password']/following::input[@type='password'][1]")
    private WebElement currentPasswordField;

    @FindBy(xpath = "//label[text()='Password']/following::input[@type='password'][1]")
    private WebElement newPasswordField;

    @FindBy(xpath = "//label[text()='Confirm Password']/following::input[@type='password'][1]")
    private WebElement confirmPasswordField;
    
    @FindBy(xpath = "//span[normalize-space()='Passwords do not match']")
    private WebElement mismatchError;
    
    @FindBy(xpath = "//label[text()='Current Password']/ancestor::div[contains(@class, 'oxd-input-group')]//span[contains(@class, 'oxd-input-field-error-message') and text()='Required']")
    private WebElement currentPasswordError;
    
    @FindBy(xpath = "//p[@class='oxd-text oxd-text--p oxd-text--toast-message oxd-toast-content-text']")
    private WebElement incorrectcurrentPasswordError;

    @FindBy(css = "button[type='submit']")
    private WebElement saveButton;

    @FindBy(css = "button[type='button'].oxd-button.oxd-button--medium.oxd-button--ghost")
    private WebElement cancelButton;

    public void enterCurrentPassword(String currentPassword) {
        currentPasswordField.sendKeys(currentPassword);
    }

    public void enterNewPassword(String newPassword) {
        newPasswordField.sendKeys(newPassword);
    }

    public void enterConfirmPassword(String confirmPassword) {
        confirmPasswordField.sendKeys(confirmPassword);
    }

    public void clickSave() {
        saveButton.click();
    }

    public void clickCancel() {
        cancelButton.click();
    }
    
    public String MismatchError () {
    	return WaitForElementVisibility(mismatchError).getText().toString();
    }
    
    public String CurrentPasswordError () {
    	return WaitForElementVisibility(currentPasswordError).getText().toString();
    }
    
    public String PasswordError () {
    	return WaitForElementVisibility(missingPassword).getText().toString();
    }
    
    public String IncorrectCurrentPasswordError () {
    	return WaitForElementVisibility(incorrectcurrentPasswordError).getText().toString();
    }
	
}
