@Integration
Feature: Essence Limits
  Essence imposes some limits on other traits and is itself limited by age

  Scenario: Solars with low Essence cannot increase traits above 5
    Given any Solar
    When I set any of her attributes to 6
    Then she has 5 dots in the attribute