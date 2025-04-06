Feature: Buzz Functionality

Background:
	Given User navigates to the OrangeHRM login page
	And User perform login operation : "Shubham" and "Testing@123"
	And User navigates to the Buzz page


Scenario: Verify Posting a Buzz with an Image
	When User clicks on the Share Photos button
	And User selects an image to upload
	And User clicks on the Share button
	Then User should see a success save message
	And The posted image should be visible on the feed

Scenario: Verify Posting an Empty Buzz
	Then The "Post" button should be disabled

Scenario Outline: Verify Display of Recently Posted Buzz
	When User posts a new buzz : "<buzzText>"
	Then User should see a success save message
	And The newly posted buzz should be displayed : "<buzzText>"

	Examples:
		| buzzText      |
		| Testing       |

Scenario: Verify Adding a Comment to a Newsfeed Post
	When User adds a comment : "Testing Comment"
	Then User should see a success save message
	And The comment should be displayed under the post : "Testing Comment"

Scenario: Verify Liking a Newsfeed Post
	When User clicks on the like button of a post
	Then The like count should increase by 1
