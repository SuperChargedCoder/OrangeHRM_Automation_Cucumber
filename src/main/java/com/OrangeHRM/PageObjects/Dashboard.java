package com.OrangeHRM.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.OrangeHRM.AbstractComponents.Reusable;

public class Dashboard extends Reusable{
	WebDriver driver;

	public Dashboard(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//i[@class='oxd-icon bi-stopwatch']")
	WebElement workClock;
	
	@FindBy(xpath = "//p[@class='oxd-userdropdown-name']")
	public WebElement profile;

	@FindBy(xpath = "//a[normalize-space()='Change Password']")
	WebElement changePassword;
	
	@FindBy(xpath = "//span[normalize-space()='My Info']")
	WebElement myInfo;
	
	@FindBy(xpath = "//span[normalize-space()='Directory']")
	WebElement directory;
	
	@FindBy(xpath = "//span[normalize-space()='Buzz']")
	WebElement buzz;
	
	public ChangePassword goToChangePasswordPage () {
		profile.click();
		WaitForElementVisibility(changePassword);
		//If navigation to change password fail, use thread.sleep
		WaitForElementClickable(changePassword).click();
		ChangePassword c = new ChangePassword(driver);
		return c;
	}
	
	public MyInfo goToMyInfo () {
		WaitForElementVisibility(myInfo).click();
		MyInfo m = new MyInfo(driver);
		return m;
	}
	
	public InOut goToInOut () {
		WaitForElementVisibility(workClock).click();
		InOut i = new InOut (driver);
		return i;
	}
	
	public Directory goToDirectory () {
		WaitForElementVisibility(directory).click();
		Directory d = new Directory (driver);
		return d;
	}
	
	public Buzz goToBuzz () {
		WaitForElementVisibility(buzz).click();
		Buzz b = new Buzz (driver);
		return b;
	}
	
}
