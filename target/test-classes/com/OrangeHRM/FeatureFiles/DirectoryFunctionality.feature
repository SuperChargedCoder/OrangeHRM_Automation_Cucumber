Feature: Directory Functionality 

Background: 
	Given User navigates to the OrangeHRM login page 
	And User perform login operation : "Shubham" and "Testing@123" 
	And User navigates to the Directory page 
	
Scenario Outline: Validate Contact Number Display 
	When User searches for employee with name "<name>" 
	Then Contact number should be "<contact_number>" 
	
	Examples: 
		| name                | contact_number   |
		| Peter Mac Anderson | 112-342-0005     |
		
Scenario Outline: Confirm Work Email Visibility 
	When User searches for employee with name "<name>" 
	Then Work email should be "<work_email>" 
	
	Examples: 
		| name                | work_email      |
		| Peter Mac Anderson | peter@osohrm.com |
		
Scenario: Test Call Functionality 
	When User searches for employee with name "Peter Mac Anderson"
	And User clicks on the call button for an employee 
	Then A pop-up message should be displayed