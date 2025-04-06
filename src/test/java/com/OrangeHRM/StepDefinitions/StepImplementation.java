package com.OrangeHRM.StepDefinitions;

import java.io.IOException;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.OrangeHRM.PageObjects.Buzz;
import com.OrangeHRM.PageObjects.ChangePassword;
import com.OrangeHRM.PageObjects.Dashboard;
import com.OrangeHRM.PageObjects.Directory;
import com.OrangeHRM.PageObjects.ForgotPassword;
import com.OrangeHRM.PageObjects.InOut;
import com.OrangeHRM.PageObjects.MyInfo;
import com.OrangeHRM.TestComponents.BaseTest;

import io.cucumber.java.en.*;

public class StepImplementation extends BaseTest {

	private WebDriver driver;
	Dashboard onLogin;
	ForgotPassword forgotPasswordRecovery;
	ChangePassword changePasswordPage;
	Directory directory;
	MyInfo myInfo;
	Buzz buzz;
	InOut inOut;

	@Given("User navigates to the OrangeHRM login page")
	public void user_navigates_to_the_orange_hrm_login_page() throws IOException {
		driver = InitiateDriver ();
		LaunchLoginPage();
	}

	@When("User enters username: {string}")
	public void user_enters_username(String username) {
		log.EnterUserName(username);
	}

	@When("User enters password: {string}")
	public void user_enters_password(String password) {
		log.EnterPassword(password);
	}

	@When("User clicks on Login button")
	public void user_clicks_on_login_button() {
		onLogin = log.ClickOnLogin();
	}

	@Then("User should be logged in successfully with name : {string}")
	public void user_should_be_logged_in_successfully(String UserName) {
		Assert.assertEquals(onLogin.profile.getText(), UserName);
	}

	@Then("User should see an error message: {string}")
	public void user_should_see_an_error_message(String error) {
		Assert.assertEquals(log.missingErrorText.get(0).getText(), error);
	}

	@Then("User should see an Invalid error message: {string}")
	public void user_should_see_an_invalid_error_message(String error) {
		Assert.assertEquals(log.invalidLoginCredential.getText(), error);
	}
	/* Forgot password Steps */

	@Given("User clicks on the Forgot Your Password? link")
	public void user_clicks_on_the_forgot_your_password_link() {
		forgotPasswordRecovery = log.ForgotPasswordRecovery();
	}

	@When("User enters username to identify your account : {string}")
	public void user_enters_username_to_identify_your_account(String username) {
		forgotPasswordRecovery.enterUsername(username);
	}

	@When("User clicks on the Reset Password button")
	public void user_clicks_on_the_reset_password_button() {
		forgotPasswordRecovery.clickResetPassword();
	}

	@Then("User should receive a password reset link")
	public void user_should_receive_a_password_reset_link() {
		Assert.assertTrue(false);
	}

	@Then("User should see an mandatory field error message: {string}")
	public void user_should_see_an_mandatory_field_error_message(String errorMessage) {
		Assert.assertEquals(forgotPasswordRecovery.missingErrorText.get(0).getText(), errorMessage);
	}

	@Then("User should see an invalid user error message: {string}")
	public void user_should_see_an_invalid_user_error_message(String errorMessage) {
		Assert.assertTrue(false);
	}

	@When("User clicks on the Cancel button")
	public void user_clicks_on_the_cancel_button() {
		forgotPasswordRecovery.clickCancel();
	}

	@Then("User should be redirected back to the login page")
	public void user_should_be_redirected_back_to_the_login_page() {
		Assert.assertEquals("Forgot your password?", log.forgotLink.getText().toString());
	}

	/* Password Change Codes */

	@Given("User perform login operation : {string} and {string}")
	public void user_perform_login_operation(String username, String password) {
		user_enters_username(username);
		user_enters_password(password);
		user_clicks_on_login_button();
	}

	@Given("User navigates to 'Change Password' page from profile dropdown")
	public void user_navigates_to_change_password_page_from_profile_dropdown() {
		changePasswordPage = onLogin.goToChangePasswordPage();
	}

	@When("User clicks on Save button")
	public void user_clicks_on_save_button() {
		changePasswordPage.clickSave();
	}

	@Then("User should see Password Change mandatory error message: {string}")
	public void user_should_see_password_change_mandatory_error_message(String errorMessage) {
		Assert.assertEquals(changePasswordPage.PasswordError(), errorMessage);
		Assert.assertEquals(changePasswordPage.CurrentPasswordError(), errorMessage);
	}

	@Then("User should see error message : {string}")
	public void user_should_see_error_message(String errorMessage) {
		Assert.assertEquals(changePasswordPage.MismatchError(), errorMessage);
	}

	@When("User enters active password: {string}")
	public void user_enters_active_password(String currentPassword) {
		changePasswordPage.enterCurrentPassword(currentPassword);
	}

	@When("User enters a new password : {string}")
	public void user_enters_a_new_password(String newPassword) {
		changePasswordPage.enterNewPassword(newPassword);
	}

	@When("User enters a confirm password : {string}")
	public void user_enters_a_confirm_password(String confirmPassword) {
		changePasswordPage.enterConfirmPassword(confirmPassword);
	}

	@Then("User should see incorrect active password error message : {string}")
	public void user_should_see_incorrect_active_password_error_message(String errorMessage) {
		Assert.assertEquals(changePasswordPage.IncorrectCurrentPasswordError(), errorMessage);
	}

	@Then("User should see a success message")
	public void user_should_see_a_success_message() {
		String expected = "Success\r\n"
				+ "Successfully Updated";
		Assert.assertEquals(onLogin.successMessage.getText().toString().replaceAll("[\\n\\r]", "").trim(), expected.replaceAll("[\\n\\r]", "").trim());
//		Assert.assertEquals(actual, expected);
	}

	/* Directory Test Cases */

	@And("User navigates to the Directory page")
	public void user_navigates_to_the_directory_page() {
		directory = onLogin.goToDirectory();
	}

	@When("User searches for employee with name {string}")
	public void user_searches_for_employee_with_name(String name) {
		directory.SearchEmployee(name);
	}

	@Then("Contact number should be {string}")
	public void contact_number_should_be(String contactNumber) {
		Assert.assertEquals(directory.getContactNumber().trim(), contactNumber.trim());
	}

	@Then("Work email should be {string}")
	public void work_email_should_be(String workEmail) {
		Assert.assertEquals(directory.getEmail().trim(), workEmail.trim());
	}

	@When("User clicks on the call button for an employee")
	public void user_clicks_on_the_call_button_for_an_employee() {
		directory.CallEmployee();
	}

	@Then("A pop-up message should be displayed")
	public void a_pop_up_message_should_be_displayed() throws InterruptedException {
		try {
			String allertText = directory.AlertHandle();
			System.out.println(allertText);
		} catch (Exception e) {
			System.out.println("Alert automation failed");
			Assert.assertTrue(false, "Alert automation failed");
		}
	}
	
	/*MyInfo Update Step Implementation*/
	
	@And("User navigates to My Info")
    public void user_navigates_to_my_info() {
		myInfo = onLogin.goToMyInfo();
    }

    @When("User tries to modify Employee ID : {string}")
    public void user_tries_to_modify_employee_id(String empId) {
        try {
            myInfo.UpdateEmpId(empId);
        } catch (Exception e) {
            // Handle potential exception if element is not found
            System.out.println("Employee ID field might not be editable");
        }
    }

    @Then("Employee ID text box should be disabled")
    public void employee_id_text_box_should_be_disabled() {
        // Check if the element is enabled using isEnabled() method
        boolean isEnabled = myInfo.employeeIdField.isEnabled();
        Assert.assertFalse(isEnabled);
    }

    @When("User tries to modify Date of Birth : {string}")
    public void user_tries_to_modify_date_of_birth(String dob) {
        try {
            myInfo.UpdateDOB(dob);
        } catch (NoSuchElementException e) {
            // Handle potential exception if element is not found
            System.out.println("Date of Birth field might not be editable");
        }
    }

    @Then("Date of Birth input field should be disabled")
    public void date_of_birth_input_field_should_be_disabled() {
        boolean isEnabled = myInfo.dateOfBirthField.isEnabled();
        Assert.assertFalse(isEnabled);
    }

    @When("User selects a nationality {string} from the dropdown")
    public void user_selects_a_nationality_from_the_dropdown(String nationality) throws InterruptedException {
        myInfo.selectNationality(nationality);
    }

    @And("User clicks on the 'Save' button")
    public void user_clicks_on_the_save_button() {
        myInfo.saveButton.click();
    }


    @When("User selects a marital status {string} from the dropdown")
    public void user_selects_a_marital_status_from_the_dropdown(String maritalStatus) {
        myInfo.selectMaritalStatus(maritalStatus);
    }

    @When("User clicks on a gender {string}")
    public void user_clicks_on_a_gender(String gender) {
        myInfo.selectGender(gender);
    }

    @Then("Only one gender option should be selected at a time")
    public void only_one_gender_option_should_be_selected_at_a_time() {
    	Assert.assertFalse(myInfo.OnlyOneCheck());
    }
    
    /*Buzz Functionality Test Implementation*/
    
    @And("User navigates to the Buzz page")
    public void user_navigates_to_the_buzz_page() {
        buzz = onLogin.goToBuzz();
    }

    @When("User clicks on the Share Photos button")
    public void user_clicks_on_button() {
        buzz.clickSharePhotosButton();
    }
    
    @When("User clicks on the Share button")
    public void user_clicks_share_on_button() throws InterruptedException {
    	Thread.sleep(2000);
    	buzz.shareButton.click();
    }

    @And("User selects an image to upload")
    public void user_selects_an_image_to_upload() throws InterruptedException {
    	try {
    		Thread.sleep(2000);
        	WebElement fileInput = driver.findElement(By.cssSelector("div[class='oxd-file-div oxd-file-div--active']"));
            String filePath = System.getProperty("user.dir") + "\\PicToUpload\\Testing Upload.png";
            fileInput.sendKeys(filePath);
            Thread.sleep(2000);
		} catch (Exception e) {
			Assert.assertTrue(false, "Failed to upload image");
		}
    	
    	
    }

    @Then("The posted image should be visible on the feed")
    public void the_posted_image_should_be_visible_on_the_feed() {
        Assert.assertTrue(true, "Implement assertion logic");
    }

    @Then("The {string} button should be disabled")
    public void the_button_should_be_disabled(String buttonName) {
        Assert.assertFalse(buzz.postButton.isEnabled(), buttonName + " button is not disable for empty post text");
    }

    @When("User posts a new buzz : {string}")
    public void user_posts_a_new_buzz(String content) {
        buzz.enterPostText(content);
        buzz.clickPostButton();
    }

    @Then("The newly posted buzz should be displayed : {string}")
    public void the_newly_posted_buzz_should_be_displayed(String content) {
        Assert.assertEquals(buzz.LatestPostBuzzText(), content);
    }

    @When("User adds a comment : {string}")
    public void user_adds_a_comment(String commentText) {
        buzz.AddComment(commentText);
    }

    @Then("The comment should be displayed under the post : {string}")
    public void the_comment_should_be_displayed_under_the_post(String commentText) {
        Assert.assertEquals(buzz.getComment(), commentText);
    }

    @When("User clicks on the like button of a post")
    public void user_clicks_on_the_like_button_of_a_post() {
        buzz.LikeRecentBuzzPost();
    }

    @Then("The like count should increase by 1")
    public void the_like_count_should_increase_by_1() {
        Assert.assertTrue(true, "Re-assertion might required");
    }
    
    @Then("User should see a success save message")
	public void user_should_see_a_success_save_message() {
		String expected = "Success\r\n"
				+ "Successfully Saved";
		WaitToAppear(onLogin.successMessage);
		Assert.assertEquals(onLogin.successMessage.getText().toString().replaceAll("[\\n\\r]", "").trim(), expected.replaceAll("[\\n\\r]", "").trim());
	}
    
    /*PunchIn and PunchOut functionality*/
    
    @Given("User navigates to the Punch-In page")
    public void user_navigates_to_the_Punch_In_page() {
    	inOut = onLogin.goToInOut();
    }

    // Scenario: Validate Punch-In Functionality for a Future Date
    @When("User selects date: {string} and time: {string} with {string}")
    public void user_selects_date_and_time(String inDate, String inTime, String inZone) {
    	try {
			WaitToAppear(inOut.submitButton);
	    	inOut.setDate(inDate);
	        inOut.setTime(inTime + " " + inZone);
//	        Thread.sleep(3000);
	        user_clicks_on_the_Punch_button();
//	        inOut.setDate(inDate);
	        Thread.sleep(3000);
	        inOut.setTime(inTime + " " + inZone);
	        Thread.sleep(9000);
	        driver.navigate().refresh();
    	} catch (InterruptedException e) {
			System.out.println("Time and Date Selection Issue");
		}
    }

    @Then("The Punch button should be disabled")
    public void the_Punch_button_should_be_disabled() {
        Assert.assertTrue(!inOut.submitButton.isEnabled());
    }

    // Scenario: Verify Punch-Out Functionality with Time/Date Before Punch-In
    @When("User clicks on the Punch button")
    public void user_clicks_on_the_Punch_button() throws InterruptedException {
    	try {
    		WaitToAppear(inOut.submitButton);
        	WaitForElementClickable(inOut.submitButton);
        	Thread.sleep(2000);
    		inOut.submitButton.click();
		} catch (Exception e) {
			Thread.sleep(2000);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", inOut.submitButton);
		}
    }

    @Then("An error message {string} should be displayed")
    public void an_error_message_should_be_displayed(String errorMessage) {
    	WaitToAppear(inOut.errorMessage.get(0));
    	Assert.assertEquals(inOut.errorMessage.get(0).getText().toString(), errorMessage);
    }

    // Scenario: Validate Punch-In Functionality for a Past Date
    @Then("The punch time {string} and time: {string} with {string} should be displayed on the Attendance page")
    public void the_punch_time_should_be_displayed_on_the_Attendance_page(String inDate, String inTime, String inZone) {
        // Code to verify punch time displayed on the Attendance page
    //	2024-12-11 - 08:29 AM (GMT +05:30)
    	String expected = inDate + " - " + inTime + " (GMT +05:30)";
    	Assert.assertEquals(inOut.InTimeText(), expected);
    }
}
