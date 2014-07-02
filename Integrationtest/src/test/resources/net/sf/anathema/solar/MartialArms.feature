@Integration
Feature: Characters can learn their native Charm
  'There is no Wind' is a Solar Archery 4/Essence 2 Root Charm.
  'Essence Arrow Attack' is a Solar Archery Root Charm and direct prerequisite to 
  'Phantom Arrow Technique', a Solar Archery 4/Essence 3 Charm.  

  Scenario: Solars can learn Martial Arts Charms
    Given a new Solar using rules for RookieLawgiver
    When I set her MartialArts to 5
    Then she can learn the Charm Solar.FistsIronTechnique
