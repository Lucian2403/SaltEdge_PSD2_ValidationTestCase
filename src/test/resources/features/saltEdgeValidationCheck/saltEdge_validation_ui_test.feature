Feature: Salt Edge Berlin Group UI Validation

  Scenario: Validate the request and response body fields
    Given user is on Berlin Group PIIS - Funds - Confirmations Section
    And user is on Response table section
    Then the Header should have the following types:
      | fundsAvailable | Boolean |