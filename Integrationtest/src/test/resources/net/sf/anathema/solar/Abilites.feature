@Integration
Feature: Anathema calculates experience point costs for Abilities

  Scenario: A Solar can learn first dots of Ability with experience
    Given any Solar
    When she goes experienced
    And  I set her Archery to 1
    Then she has 1 dots in ability Archery