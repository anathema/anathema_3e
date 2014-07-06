@Integration
Feature: Anathema calculates experience point costs for Abilities of Mortal

  Scenario: A Mortal can learn a first dot in an ability for 3 experience points
    Given a new Mortal using rules for EverydayHero
    When she goes experienced
    And  I set her Archery to 1
    Then she has spent 3 xp points
