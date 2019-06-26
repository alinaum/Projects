Feature: Add item to favority list 
    Background: User is logged in
        Given I'm in the home page to press heart
        And I go to login
        And I fill my user name and password
        Then I should be logged in

    Scenario: Client logged in can add item to favority list
        Given that I click on the Todas as categorias tab
        And I choose one category
        And I choose the first item from the category
        And I choose the first display item
        And I click on favority list icon
        Then the item is added to favority list
