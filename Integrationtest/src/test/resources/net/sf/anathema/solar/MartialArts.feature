@Integration
Feature: Solars can learn Charms from the Martial Arts category
  'MartialArts.CrimsonLeapingCatTechnique' is a Martial Arts 3/Essence 1 Root Charm from Tiger Style.

  Scenario: You cannot become a Martial Artist without being a Brawler first
    Given any Solar
    Then she can not earn the Martial Artist merit

  Scenario: Given experience in Brawling, one can beome a Martial Artist
    Given any Solar
    When I set her Brawl to 1
    Then she can earn the Martial Artist merit

  Scenario: Only Martial Artists can learn Martial Arts styles 
    Given any Solar
    When she is not a Martial Artist
    Then she cannot learn TigerStyle martial arts  
    
  Scenario: Each Style is a separate ability
    Given any Solar
    And she is a Martial Artist
    When I set her TigerStyle to 3
    Then she has 3 dots in TigerStyle

  Scenario: Characters have an overall Martial Arts rating equal from their best-known style
    Given any Solar
    And she is a Martial Artist
    When I set her CraneStyle to 3
    And I set her TigerStyle to 4
    Then she has 4 dots in MartialArts

  Scenario: Characters need proficiency in the a style to learn Charms for that style
    Given any Solar
    And she is a Martial Artist
    When I set her TigerStyle to 3
    Then she can learn the Charm MartialArts.CrimsonLeapingCatTechnique

  Scenario: Another Style does not suffice
    Given any Solar
    And she is a Martial Artist
    When I set her CraneStyle to 3
    Then she can not learn the Charm MartialArts.CrimsonLeapingCatTechnique

  Scenario: A Solar pays for Martial Arts as for Abilities (Made the path to Martial Arts explicit so the calculation is more obvious)
    Given any Solar
    And I set her Brawl to 1
    And she earns the Martial Artist merit
    When I set her TigerStyle to 4
    Then she has 4 ability dots spent     
    And she has spent 2 bonus points

  Scenario: A Solar pays experience for Martial Arts as for Abilities
    Given any Solar
    And she is a Martial Artist
    And  I set her TigerStyle to 1
    And she goes experienced
    When I set her TigerStyle to 2
    Then she has spent 2 xp points

  Scenario: The state of all Martial Arts is tied to the state of Brawl
    Given any Solar
    And she is a Martial Artist
    And I favor her Brawl
    And she goes experienced
    When I set her TigerStyle to 1
    Then she has spent 3 xp points