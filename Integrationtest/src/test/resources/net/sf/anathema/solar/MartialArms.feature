@Integration
Feature: Solars can learn Charms from the Martial Arts category
  'Fists of Iron Technique' is a Martial Arts 2/Essence 1 Root Charm.

  Scenario: Solars can learn Martial Arts Charms
    Given a new Solar using rules for RookieLawgiver
    When I set her MartialArts to 5
    Then she can learn the Charm Solar.FistsIronTechnique
