@Integration
Feature: Essence Limits
  Essence imposes some limits on other traits and is itself limited by age

  Scenario: Solars with low Essence cannot increase traits above 5
    Given any Solar
    When I set any of her attributes to 6
    Then she has 5 dots in the attribute

  Scenario: Solars with high Essence can raise traits  above 5
    Given any Solar
    And she has been exalted for 100 years
    And I set her Essence to 6
    When I set any of her attributes to 6
    Then she has 6 dots in the attribute
    
  Scenario Outline: Essence depends on the character's age
    Given any Solar
    And she has been exalted for <age> years
    When I set her Essence to 10
    Then she has <value> dots in Essence

  Examples:
    | age  | value |
    | 99   | 5     |
    | 100  | 6     |
    | 249  | 6     |
    | 250  | 7     |
    | 499  | 7     |
    | 500  | 8     |
    | 999  | 8     |
    | 1000 | 10    |