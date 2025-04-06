package com.OrangeHRM.PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.OrangeHRM.AbstractComponents.Reusable;

public class InOut extends Reusable {

	WebDriver driver;

	public InOut(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = ".oxd-date-input input")
    WebElement dateInput;

    @FindBy(css = ".oxd-time-input input")
    WebElement timeInput;

    @FindBy(css = ".oxd-textarea")
    WebElement noteInput;

    @FindBy(xpath = "//button[@type='submit']")
    public WebElement submitButton;
    
    @FindBy(xpath = "//span[@class='oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message']")
    public List<WebElement> errorMessage;

    // ... other elements for punch-out button, error messages, etc.

    public void setDate(String date) {
        dateInput.sendKeys(date);
    }
    
    public void setTime(String time) {
    	timeInput.sendKeys(time);
    }

    public void setNote(String note) {
        noteInput.sendKeys(note);
    }

    public void clickInButton() {
    	submitButton.click();
    }
    
    @FindBy(xpath = "//p[@class='oxd-text oxd-text--p oxd-text--subtitle-2']")
    WebElement punchInTime;
    
    public String InTimeText() {
    	return WaitForElementVisibility(punchInTime).getText().toString();
    }
    
}
