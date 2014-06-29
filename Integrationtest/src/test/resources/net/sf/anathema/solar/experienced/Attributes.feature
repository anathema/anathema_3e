@Integration
Feature: Attribute Handling of big damn heroes

  Scenario: A big damn hero starts with all her attributes with 1 dot
    Given a new Solar using rules for BigDamnHero
    Then she has 1 dots in all her attributes

  Scenario Outline: A big damn hero respects boundaries of all attributes while creating
    Given a new Solar using rules for BigDamnHero
    When I set any of her attributes to <illegalValue>
    Then she has <legalValue> dots in the attribute

  Examples:
    | illegalValue | legalValue |
    | 6            | 5          |
    | 0            | 1          |

  Scenario: A big damn hero must not lower her attributes below creation value while experiencing
    Given a new Solar using rules for BigDamnHero
    When I set any of her attributes to 3
    And she goes experienced
    And I set the attribute to 2
    Then she has 3 dots in the attribute
