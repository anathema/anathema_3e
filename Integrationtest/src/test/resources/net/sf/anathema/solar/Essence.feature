Feature: Essence
  Essence cannot be raised as other traits but grows with experience.

  Scenario: Solars cannot increase their Essence value manually
    Given any Solar
    When I set her Essence to 2
    Then she has 1 dots in Essence