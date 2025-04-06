Feature: Password Change Functionality 

Background: 
	Given User navigates to the OrangeHRM login page 
	And User perform login operation : "Shubham" and "Testing@123"
	And User navigates to 'Change Password' page from profile dropdown 
	
Scenario: Verify Password Change with No Input Provided 
	And User clicks on Save button 
	Then User should see Password Change mandatory error message: "Required" 
	And User should see error message : "Passwords do not match" 
	
Scenario: Verify Password Change When New Password and Confirm Password Do Not Match 
	When User enters active password: "password" 
	And User enters a new password : "NewPassword" 
	And User enters a confirm password : "DifferentPassword" 
	And User clicks on Save button 
	Then User should see error message : "Passwords do not match" 
	
Scenario: Verify Password Change Using an Incorrect Current Password 
	When User enters active password: "Testing@789" 
	And User enters a new password : "NewPassword@123" 
	And User enters a confirm password : "NewPassword@123" 
	And User clicks on Save button 
	Then User should see incorrect active password error message : "Current Password is Incorrect" 
	
@Skip
Scenario: Verify Password Change with Valid Current and New Password Details 
	When User enters active password: "Testing@123" 
	And User enters a new password : "NewPassword@123" 
	And User enters a confirm password : "NewPassword@123" 
	And User clicks on Save button 
	Then User should see a success save message
	