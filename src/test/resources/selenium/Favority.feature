Feature: Add item to favority list 

Scenario: Client logged in can add item to favority list
    Given that I'm looged in
    And I'm on the home page
    And I click on the Todas as categorias tab
    And I choose one item
    And I choose the firt item on the display page
    And I click on favority list icon
    Then the item is added to favority list
