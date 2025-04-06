package com.OrangeHRM.PageObjects;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.OrangeHRM.AbstractComponents.Reusable;

public class Buzz extends Reusable {
	
	WebDriver driver;

	public Buzz(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = ".oxd-buzz-post-input")
    WebElement postInputFieldText;

    @FindBy(css = ".oxd-button.oxd-button--medium.oxd-button--main")
    public WebElement postButton;

    @FindBy(xpath = "//button[normalize-space()='Share Photos']")
    WebElement sharePhotosButton;
    
    @FindBy(css = "div[class='oxd-form-actions orangehrm-buzz-post-modal-actions'] button:nth-child(1)")
    public WebElement shareButton;
    
    @FindBy(xpath = "//button[normalize-space()='Share Video']")
    WebElement shareVideoButton;
    
    @FindBy(css = "p[class='oxd-text oxd-text--p orangehrm-buzz-post-body-text']")
    List<WebElement> postedBuzz;
    
    @FindBy(css = "i[class='oxd-icon bi-chat-text-fill']")
    List<WebElement> buzzCommentButton;
    
    @FindBy(css = "input[placeholder = 'Write your comment...'] ")
    WebElement commentTextBox;
    
    @FindBy(css = "path[id='heart']")
    List<WebElement> likePost;
    
    @FindBy(css = "span[class = 'oxd-text oxd-text--span orangehrm-post-comment-text']")
    List<WebElement> buzzcommentText;

    public void enterPostText(String text) {
    	postInputFieldText.sendKeys(text);
    }

    public void clickPostButton() {
        postButton.click();
    }

    public void clickSharePhotosButton() {
        sharePhotosButton.click();
        //Add code to upload file
    }

    public void clickShareVideoButton() {
        shareVideoButton.click();
    }
    
    public String LatestPostBuzzText () {
    	WaitForElementVisibility(postedBuzz.get(0));
    	return postedBuzz.get(0).getText().toString();
    }
    
    public void AddComment (String comment) {
    	buzzCommentButton.get(0).click();
    	WaitForElementVisibility(commentTextBox).sendKeys(comment);
    	commentTextBox.sendKeys(Keys.ENTER);
    }
    
    public void LikeRecentBuzzPost () {
    	likePost.get(0).click();
    }
    
    @FindBy(css = "span[class='oxd-text oxd-text--span orangehrm-post-comment-text']")
    WebElement firstPostComment;
    
    public String getComment() {
    	return firstPostComment.getText();
    }
    
}
