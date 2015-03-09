Feature: Essence
  Essence cannot be raised as other traits but grows with experience.

  Scenario: Rookies start with Essence 1
    Given a new Solar using rules for RookieLawgiver
    Then she has 1 dots in Essence
  
  Scenario Outline: Essence grows with experience
    Given a new Solar using rules for RookieLawgiver
    And she goes experienced
    When she gains <xp> experience points
    And she spends <xp> experience points
    Then she has <essence> dots in Essence

  Examples:
    | xp  | essence |
    | 50  | 2       |
    | 120 | 3       |
    | 200 | 4       |
    | 300 | 5       |