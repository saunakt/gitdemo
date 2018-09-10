Feature: FACEBOOK Login Test
#As a valid user I should be able to log into FACEBOOK with correct emailId and password

Scenario: FACEBOOK Login 

Given User is on FACEBOOK page
And User enters EmailId as "XXXX@gmail.com" and Password as "XXX" 
And Clicks on LogIn Button
Then User lands into Home Page