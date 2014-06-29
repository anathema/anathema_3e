@Integration
Feature: Attribute costs of everyday heroes

  Scenario: A fresh everyday hero does not pay for Attributes
    Given a new Mortal using rules for EverydayHero
    Then she has spent 0 points on Primary Attributes
    And she has spent 0 points on Secondary Attributes
    And she has spent 0 points on Tertiary Attributes
    And she has spent 0 bonus points

  Scenario: Everyday heroes assign groups based on actual spending
    Given a new Mortal using rules for EverydayHero
    When she spends all her Attribute Freebies
    Then she has spent 6 points on Primary Attributes
    And she has spent 4 points on Secondary Attributes
    And she has spent 3 points on Tertiary Attributes
    And she has spent 0 bonus points

  Scenario Outline: Everyday Heroes spend bonus points for Attributes depending on the group's priority
    Given a new Mortal using rules for EverydayHero
    When she spends all her Attribute Freebies
    And she spends one additional dot in <priority> Attributes
    Then she has spent <cost> bonus points

  Examples:
    | priority  | cost |
    | Tertiary  | 3    |
    | Secondary | 4    |
    | Primary   | 5    |
