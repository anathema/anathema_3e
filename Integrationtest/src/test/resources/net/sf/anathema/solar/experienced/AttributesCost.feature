@Integration
Feature: Attribute costs of big damn heroes

  Scenario: A fresh big damn hero does not pay for Attributes
    Given a new Solar using rules for BigDamnHero
    Then she has spent 0 points on Primary Attributes
    And she has spent 0 points on Secondary Attributes
    And she has spent 0 points on Tertiary Attributes
    And she has spent 0 bonus points

  Scenario: Big damn heroes assign groups based on actual spending
    Given a new Solar using rules for BigDamnHero
    When she spends all her Attribute Freebies
    Then she has spent 8 points on Primary Attributes
    And she has spent 6 points on Secondary Attributes
    And she has spent 4 points on Tertiary Attributes
    And she has spent 0 bonus points

  Scenario Outline: Big Damn Heroes spend bonus points for Attributes depending on the group's priority
    Given a new Solar using rules for BigDamnHero
    When she spends all her Attribute Freebies
    And she spends one additional dot in <priority> Attributes
    Then she has spent <cost> bonus points

  Examples:
    | priority  | cost |
    | Tertiary  | 3    |
    | Secondary | 4    |
    | Primary   | 5    |
