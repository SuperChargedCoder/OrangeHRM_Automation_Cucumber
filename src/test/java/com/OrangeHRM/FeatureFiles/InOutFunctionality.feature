@Skip
Feature: Punch-In and Punch-Out Functionality 

Background: 
	Given User navigates to the OrangeHRM login page 
	And User perform login operation : "shubham" and "Testing@123" 
	And User navigates to the Punch-In page 

Scenario Outline: Validate Punch-In Functionality for a Future Date 
	When User selects date: "<inDate>" and time: "<inTime>" with "<inZone>" 
	Then The Punch button should be disabled 
	
	Examples: 
		| inDate   | inTime | inZone |
		| 2027-15-10  | 09:00  | AM   |

		
Scenario Outline: Verify Punch-Out Functionality with Time/Date Before Punch-In 
	When User selects date: "<inDate>" and time: "<inTime>" with "<inZone>" 
	And User clicks on the Punch button 
	Then User should see a success save message 
	When User selects date: "<outDate>" and time: "<outTime>" with "<outZone>" 
	And User clicks on the Punch button 
	Then An error message "Punch out Time Should Be Later Than Punch in Time" should be displayed 
	
	Examples: 
		| inDate   | inTime | inZone | outDate   | outTime | outZone | 
		| 2024-15-11 | 11:00  | AM     | 2024-15-11  | 08:00     | AM      | 
		
Scenario Outline: Verify Punch-Out Functionality with Time/Date After Punch-In 
	When User selects date: "<inDate>" and time: "<inTime>" with "<inZone>" 
	And User clicks on the Punch button 
	Then User should see a success save message 
	When User selects date: "<inDate>" and time: "<inTime>" with "<inZone>" 
	And User clicks on the Punch button 
	Then User should see a success save message 
	
	Examples: 
		| inDate   | inTime | inZone | outDate   | outTime | outZone | 
		| 2024-12-11 | 10:00  | AM     | 2024-12-11  | 06:00     | PM      | 
		
Scenario Outline: Check Punch-Out Functionality for Overlapping Time Entries 
	When User selects date: "<inDate>" and time: "<inTime>" with "<inZone>" 
	And User clicks on the Punch button 
	Then User should see a success save message 
	When User selects date: "<inDate>" and time: "<inTime>" with "<inZone>" 
	And User clicks on the Punch button 
	Then User should see a success save message 
	When User selects date: "<inDate>" and time: "<inTime>" with "<inZone>" 
	And User clicks on the Punch button 
	Then An error message "Overlapping Records Found" should be displayed 
	
	Examples: 
		| inDate   | inTime | inZone | outDate   | outTime | outZone | 
		| 2023-11-21 | 10:00  | AM     | 2023-11-21  | 15:00     | PM      | 
		
Scenario Outline: Validate Punch-In Functionality for a Past Date 
	When User selects date: "<inDate>" and time: "<inTime>" with "<inZone>" 
	And User clicks on the Punch button 
	Then User should see a success save message 
	And The punch time "<inDate>" and time: "<inTime>" with "<inZone>" should be displayed on the Attendance page 
	
	Examples: 
		| inDate   | inTime | inZone |
		| 2024-09-21  | 10:00  | AM   |
