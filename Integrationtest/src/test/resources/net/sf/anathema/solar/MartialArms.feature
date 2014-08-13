@Integration
Feature: Solars can learn Charms from the Martial Arts category
  'Crimson Leaping Cat Technique' is a Martial Arts 3/Essence 1 Root Charm.

  Scenario: Solars can learn Martial Arts Charms
    Given any Solar
    When I set her MartialArts to 3
    Then she can learn the Charm Crimson Leaping Cat Technique
