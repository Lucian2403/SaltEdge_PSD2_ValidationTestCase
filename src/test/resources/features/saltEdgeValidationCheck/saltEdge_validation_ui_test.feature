Feature: Salt Edge Berlin Group UI Validation

  Scenario: Validate the request and response body fields
    Given user is on Berlin Group PIS - Payments - Create Section
    And user is on Response table section
    Then the headers should have the following types:
      | Header Name   | Type   |
      | X-Request-ID  | String |
      | Digest  | String |
      | Date | Datetime |