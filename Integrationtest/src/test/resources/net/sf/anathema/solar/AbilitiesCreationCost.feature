@Integration
Feature: Anathema calculates creation point costs for Abilities
  The scenarios for the various cases of overspending work with a fresh BigDamnHero so we can use their allotment.

  Scenario: A Solar pays general dots for favored Abilities
    Given any Solar
    When I favor her Archery
    And I set her Archery to 1
    Then she has 1 ability dots spent
    And she has spent 0 bonus points

  Scenario: A fresh Solar does not pay for Abilities
    Given any Solar
    Then she has 0 ability dots spent
    And she has spent 0 bonus points

  Scenario: A Solar pays general dots for general Abilities
    Given any Solar
    When I set her Archery to 1
    Then she has 1 ability dots spent
    And she has spent 0 bonus points

  Scenario: A Solar pays bonus when she raises an Ability above the threshold
    Given any Solar
    When I set her Archery to 4
    Then she has 3 ability dots spent
    And she has spent 2 bonus points

  Scenario: A Solar pays bonus points when she overspends on Abilities
    Given any Solar
    When she exceeds her Ability allotment by 1 dot
    Then she has all her ability dots spent
    And she has spent 2 bonus points
