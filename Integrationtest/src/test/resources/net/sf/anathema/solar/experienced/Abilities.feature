@Integration
Feature: Anathema calculates creation point costs for Abilities
  The scenarios for the various cases of overspending work with a fresh BigDamnHero so we can use their allotment.

  Scenario: A Solar has minimum value of 1 in a favored Ability
    Given a new Solar using rules for BigDamnHero
    When I favor her Archery
    Then she has 1 dots in ability Archery
