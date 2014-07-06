@Integration
Feature: Anathema calculates experience point costs for Abilities for Mortals

  Scenario: A Mortal can learn first dots of Ability with experience
    Given a new Mortal using rules for EverydayHero
    When she goes experienced
    And  I set her Archery to 1
    Then she has 1 dots in ability Archery