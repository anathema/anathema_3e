@Integration
Feature: Attribute Handling of rookie lawgiver

  Scenario: A rookie lawgiver starts with all her attributes with 1 dot
    Given a new Solar using rules for RookieLawgiver
    Then she has 1 dots in all her attributes

  Scenario Outline: A rookie lawgiver respects boundaries of all attributes while creating
    Given a new Solar using rules for RookieLawgiver
    When I set any of her attributes to <illegalValue>
    Then she has <legalValue> dots in the attribute

  Examples:
    | illegalValue | legalValue |
    | 6            | 5          |
    | 0            | 1          |

  Scenario: An rookie lawgiver must not lower her attributes below creation value while experiencing
    Given a new Solar using rules for RookieLawgiver
    When I set any of her attributes to 3
    And she goes experienced
    And I set the attribute to 2
    Then she has 3 dots in the attribute
