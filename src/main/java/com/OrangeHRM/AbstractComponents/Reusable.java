package com.OrangeHRM.AbstractComponents;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Reusable {
	
	WebDriver driver;
	
	public Reusable (WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//div[@class='oxd-toast-content oxd-toast-content--success']")
	public WebElement successMessage;
	
	@FindBy(xpath = "//label[text()='Password']/ancestor::div[contains(@class, 'oxd-input-group')]//span[contains(@class, 'oxd-input-field-error-message') and text()='Required']")
	public WebElement missingPassword;
	
	@FindBy(css = "span[class='oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message']")
	public List<WebElement> missingErrorText;
	
	
	public WebElement WaitForElementVisibility(WebElement Element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		return wait.until(ExpectedConditions.visibilityOf(Element));
	}
	
	public WebElement WaitForElementClickable(WebElement Element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		return wait.until(ExpectedConditions.elementToBeClickable(Element));
	}
	
	public void scrollIntoView(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}

}
