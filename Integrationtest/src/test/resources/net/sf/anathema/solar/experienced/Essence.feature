@Integration
Feature: Essence
  Essence cannot be raised as other traits but grows with experience.

  Scenario: Big Heroes start with Essence 2
    Given a new Solar using rules for BigDamnHero
    Then she has 2 dots in Essence
  
  Scenario Outline: Essence grows with experience
    Given a new Solar using rules for BigDamnHero
    And she goes experienced
    When she gains <xp> experience points
    And she spends <xp> experience points
    Then she has <essence> dots in Essence

  Examples:
    | xp  | essence |
    | 75  | 3       |
    | 150 | 4       |
    | 250 | 5       |