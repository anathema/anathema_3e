@Integration
Feature: Anathema calculates experience point costs for Abilities

  Scenario: A Solar can learn a first dot in an ability for 3 experience points
    Given any Solar
    When she goes experienced
    And  I set her Archery to 1
    Then she has spent 3 xp points

  Scenario: A Solar can learn the second dot in an ability for 2 experience points
    Given any Solar
    And  I set her Archery to 1
    When she goes experienced
    Then  I set her Archery to 2
    And she has spent 2 xp points

  Scenario: A Solar can learn the second dot in an ability for 4 experience points
    Given any Solar
    And  I set her Archery to 2
    When she goes experienced
    Then  I set her Archery to 3
    And she has spent 4 xp points

  Scenario: A Solar can learn the second dot in a favored ability for 1 experience points
    Given any Solar
    When I favor her Archery
    And  I set her Archery to 1
    And she goes experienced
    Then  I set her Archery to 2
    And she has spent 1 xp points

  Scenario: A Solar can learn the second dot in a favored ability for 3 experience points
    Given any Solar
    When I favor her Archery
    And  I set her Archery to 2
    And she goes experienced
    Then  I set her Archery to 3
    And she has spent 3 xp points
