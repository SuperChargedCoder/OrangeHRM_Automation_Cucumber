Feature: Profile Update Functionality 

Background: 
	Given User navigates to the OrangeHRM login page 
	And User perform login operation : "Shubham" and "Testing@123" 
	And User navigates to My Info
	
Scenario: Validate Employee ID Update Functionality 
	When User tries to modify Employee ID : "EmpId" 
	Then Employee ID text box should be disabled 
	
Scenario: Validate Date of Birth Update Functionality 
	When User tries to modify Date of Birth : "20-07-1999" 
	Then Date of Birth input field should be disabled 
	
@Skip
Scenario: Validate Nationality Update Functionality 
	When User selects a nationality "India" from the dropdown 
	And User clicks on the 'Save' button 
	Then User should see a success message 
	
@Skip
Scenario: Validate Marital Status Update Functionality 
	When User selects a marital status "Single" from the dropdown 
	And User clicks on the 'Save' button 
	Then User should see a success message
	
Scenario: Validate Gender Update Functionality 
	When User clicks on a gender "Male" 
	And Only one gender option should be selected at a time 
	And User clicks on the 'Save' button 
	Then User should see a success message 
