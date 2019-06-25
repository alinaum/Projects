Feature: User Account 
    Background: User is on Login page 
        Given that I'm in login page
        Then I should be able to see the login page
	
	Scenario: Client can create a new a account	
        Given that I informe my email
        And I click in Prosseguir
        When I fill the mandatory fields 
        And I press Continuar
        Then my new account is created
   	
	Scenario: Account can't be create if one of te mandatory fields is missing
        Given that I informe my email
        And I click in Prosseguir
        When I do not all fill the mandatory fields 
        And I press Continuar
        Then The error message is displayed