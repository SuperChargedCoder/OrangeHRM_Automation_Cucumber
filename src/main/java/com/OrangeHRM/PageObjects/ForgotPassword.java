package com.OrangeHRM.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.OrangeHRM.AbstractComponents.Reusable;

public class ForgotPassword extends Reusable {
	
	WebDriver driver;

	public ForgotPassword(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//input[@placeholder='Username']")
    private WebElement usernameField;

    @FindBy(css = "button[type='submit']")
    private WebElement resetPasswordButton;

    @FindBy(css = "button[type='button']")
    private WebElement cancelButton;

    @FindBy(xpath = "//h6[@class='oxd-text oxd-text--h6 orangehrm-forgot-password-title']")
    private WebElement emailTrigger;

    public void enterUsername(String username) {
        usernameField.sendKeys(username);
    }

    public void clickResetPassword() {
        resetPasswordButton.click();
    }

    public void clickCancel() {
        cancelButton.click();
    }
    
    public String ResetResponse () {
    	return WaitForElementVisibility(emailTrigger).getText().toString();
    }

}
