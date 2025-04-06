package com.OrangeHRM.PageObjects;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.OrangeHRM.AbstractComponents.Reusable;

public class Login extends Reusable {
	
	WebDriver driver;

	public Login (WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//input[@placeholder='Username']")
	WebElement username;
	
	@FindBy(xpath = "//input[@placeholder='Password']")
	WebElement password;
	
	@FindBy(xpath = "//button[@type='submit']")
	WebElement loginButton;
	
	@FindBy(xpath = "//p[@class='oxd-text oxd-text--p orangehrm-login-forgot-header']")
	public WebElement forgotLink;
	
	@FindBy(xpath = "//p[@class='oxd-text oxd-text--p oxd-alert-content-text']")
	public WebElement invalidLoginCredential;
	
	
	public void EnterUserName (String name) {
		username.sendKeys(name);
	}
	
	public void EnterPassword (String passkey) {
		password.sendKeys(passkey);
	}
	
	public Dashboard ClickOnLogin () {
		loginButton.click();
		Dashboard d = new Dashboard(driver);
		return d;
	}
	
	public ForgotPassword ForgotPasswordRecovery () {
		forgotLink.click();
		ForgotPassword f = new ForgotPassword(driver);
		return f;
	}
	
	public String InvalidLoginError () {
		return WaitForElementVisibility(invalidLoginCredential).getText().toString();
	}
	
	public String MissingUserNameError () {
		return WaitForElementVisibility(missingErrorText.get(0)).getText().toString();
	}
	
	public String MissingPasswordError () {
		return WaitForElementVisibility(missingPassword).getText().toString();
	}

}
