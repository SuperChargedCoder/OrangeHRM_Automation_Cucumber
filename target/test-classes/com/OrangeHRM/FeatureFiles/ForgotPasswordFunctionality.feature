Feature: OrangeHRM Password Reset Functionality 

Background: 
	Given User navigates to the OrangeHRM login page 
	And User clicks on the Forgot Your Password? link 
	

Scenario Outline: Verify Password Reset Functionality with a Valid Username 
	When User enters username to identify your account : "<username>" 
	And User clicks on the Reset Password button 
	Then User should receive a password reset link 
	
	Examples: 
		| username |
		| shubham     |
		
Scenario: Verify Password Reset Process When Username Field is Left Empty 
	And User clicks on the Reset Password button 
	Then User should see an mandatory field error message: "Required"
	
Scenario Outline: Verify Password Reset Functionality with an Invalid Username 
	When User enters username to identify your account : "<username>"  
	And User clicks on the Reset Password button 
	Then User should see an invalid user error message: "Invalid username" 
	
	Examples: 
		| username      |
		| invalid_user  |
		
Scenario: Verify User Navigation Upon Clicking the Cancel Button 
	When User clicks on the Cancel button 
	Then User should be redirected back to the login page