Feature: Register Facebook
  I want to register Facebook. So that I can access my personal profile
  
  @tag1
  Scenario: System user signs with valid credentials
    Given Im in a role of "Sign Up" user 
    When I open the Facebook page
    Then The system will show the “Registration page” section below existing user
    And The system will show the list field of first name, last name, email or phone number, birthday, gender
    When I fill all the list of fields "Fadel", "Januar", "testFB021@gmail.com", "P@ssw0rd021", "20 Jan 2003" and "Male"
    And I click the Register button
    Then the system will save my data
    And will bring me to my profile Facebook Homepage
    
  
		