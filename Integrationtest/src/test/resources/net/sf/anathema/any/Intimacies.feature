@Integration
Feature: Characters have a number of intimacies detailing their hearts and minds

  Scenario: Intimacies change the character
    Given a new Character of any kind
    When I add a fresh Intimacy
    Then the character needs to be saved

  Scenario: Characters can learn any number of Intimacies
    Given a new Character of any kind
    When I add many Intimacies
    Then the character has as many Intimacies 