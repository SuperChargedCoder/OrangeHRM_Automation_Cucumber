Feature: OrangeHRM Login Functionality 

Background: 
	Given User navigates to the OrangeHRM login page

	
Scenario Outline: Successful Login with Valid Credentials 
	When User enters username: "<username>" 
	And User enters password: "<password>" 
	And User clicks on Login button 
	Then User should be logged in successfully with name : "<name>"
	
	Examples: 
		| username | password | name |
		| shubham    | Testing@123 | Shubham Chaurasia |
		
Scenario Outline: Login Error Message with Invalid Credentials 
	When User enters username: "<username>"
	And User enters password: "<password>"
	And User clicks on Login button 
	Then User should see an Invalid error message: "Invalid credentials" 
	
	Examples: 
		| username      | password      |
		| invalid_user  | invalid_pass  |

		
Scenario: Login Error Handling for Missing Username 
	And User enters password: "password" 
	And User clicks on Login button 
	Then User should see an error message: "Required" 
	
Scenario: Login Error Handling for Missing Password 
	When User enters username: "username" 
	And User clicks on Login button 
	Then User should see an error message: "Required"