package com.OrangeHRM.PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.OrangeHRM.AbstractComponents.Reusable;

public class Directory extends Reusable {
	
	WebDriver driver;

	public Directory(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//input[@placeholder='Type for hints...']")
	WebElement empName;
	
	@FindBy(xpath = "//div[@class='oxd-autocomplete-text-input--after']")
	List<WebElement> autoSuggest;
	
	@FindBy(css = "p[class='oxd-text oxd-text--p orangehrm-directory-card-header --break-words']")
	List<WebElement> empNames;
	
	@FindBy(xpath = "//button[@type='submit']")
	WebElement search;
	
	@FindBy(xpath = "//div[@class='oxd-sheet oxd-sheet--rounded oxd-sheet--white orangehrm-directory-card']")
	List<WebElement> searchResult;
	
	@FindBy(xpath = "//p[normalize-space()='112-342-0005']")
	WebElement telephone;
	
	@FindBy(xpath = "//p[normalize-space()='peter@osohrm.com']")
	WebElement email;
	
	@FindBy(xpath = "//button[@class='oxd-icon-button oxd-icon-button--success']")
	WebElement call;
	
	public void SearchEmployee (String name) {
		WaitForElementVisibility(empNames.get(0));
		for (WebElement empName : empNames) {
			if (empName.getText().trim().equalsIgnoreCase(name)) {
				empName.click();
			}
		}
		/*
		empName.sendKeys(name);
		autoSuggest.get(0).click();
		search.click();;
		try {
			searchResult.get(0).click();
		} catch (Exception e) {
			System.out.println("Employee Search Fail");
		}
		*/
	}
	
	public String getContactNumber () {
		return telephone.getText().toString();
	}
	
	public String getEmail () {
		return email.getText().toString();
	}
	
	public void CallEmployee () {
		call.click();
	}
	
	public String AlertHandle () throws InterruptedException {
		Thread.sleep(2000);
		return driver.switchTo().alert().getText();
	}
	
}
