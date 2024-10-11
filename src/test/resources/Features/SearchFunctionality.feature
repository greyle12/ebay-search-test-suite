Feature: eBay Search Functionality

  Scenario Outline: Valid search returns relevant results
    Given I am on eBay homepage
    When I enter "<searchTerm>" into the search bar
    And I click on the search button
    Then I should see results related to "<searchTerm>"

    Examples: 
      | searchTerm       |
      | laptop           |
      | children's books |
      | headphones       |
      | 电脑              |

 Scenario:  Valid search returns and valid pagination
    Given I am on eBay homepage
    When I enter "shoe" into the search bar
    And I click on the search button
    Then I should see results related to "shoe" then click on the next page button
    Then I should see results related to "shoe"
    
 Scenario:  Valid input and getting search suggestions
    Given I am on eBay homepage
    When I enter "lapto" into the search bar
    Then I should see some search suggestions related to "lapto"

  Scenario: Empty search input
    Given I am on eBay homepage
    When I leave the search bar empty
    And I click on the search button
    Then I should see the homepage or a prompt to enter a search term

  Scenario: Invalid search input(only spaces)
    Given I am on eBay homepage
    When I enter "  " into the search bar
    And I click on the search button
    Then I should see a safe error message or no results

  Scenario: Invalid search input
    Given I am on eBay homepage
    When I enter "!@#$%^&*()" into the search bar
    And I click on the search button
    Then I should see a safe error message or no results

  Scenario: SQL injection attempt returns an error page
    Given I am on eBay homepage
    When I enter "'; DROP TABLE users;" into the search bar
    And I click on the search button
    Then I should see an error page with message "Bad Request"
    
    Scenario: XSS attack vector attempt returns an error message or no results displayed
    Given I am on eBay homepage
    When I enter "<script>alert('test')</script>" into the search bar
    And I click on the search button
    Then I should see a safe error message or no results

  Scenario: No results found
    Given I am on eBay homepage
    When I enter "xyzabc123" into the search bar
    And I click on the search button
    Then I should see a "0 results" message

  Scenario: Search with extremely long input
    Given I am on eBay homepage
    When I enter a string of 1000 characters into the search bar
    And I click on the search button
    Then I should see a safe error message or no results
