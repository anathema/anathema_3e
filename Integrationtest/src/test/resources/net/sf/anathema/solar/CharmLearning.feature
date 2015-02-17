@Integration
Feature: Characters can learn their native Charm
  'Solar.AilmentRectifyingMethod' is a Solar Medicine 1/Essence 1 Root Charm.
  'Solar.PlagueBanishingIncitation' is a Solar Medicine charm that needs Medicine 3, Essence 1 and 'Solar.AilmentRectifyingMethod'

  Scenario: Charm is not learnable if prerequisites are not met
    Given any Solar
    When I set her Medicine to 0
    Then she can not learn the Charm Solar.AilmentRectifyingMethod

  Scenario: Charm is learnable if prerequisites are met
    Given any Solar
    When I set her Medicine to 1
    Then she can learn the Charm Solar.AilmentRectifyingMethod

  Scenario: Charm are unlearnable for experienced characters
    Given any Solar
    And I set her Medicine to 1
    And she goes experienced
    When she learns the Charm Solar.AilmentRectifyingMethod
    And she forgets the Charm Solar.AilmentRectifyingMethod
    Then she does not know the Charm Solar.AilmentRectifyingMethod
    
  Scenario: Parent Charms are automatically learned
    Given any Solar
    And I set her Medicine to 3
    When she learns the Charm Solar.PlagueBanishingIncitation
    Then she knows the Charm Solar.AilmentRectifyingMethod

  Scenario: Child Charms are automatically forgotten
    Given any Solar
    And I set her Medicine to 3
    And she learns the Charm Solar.PlagueBanishingIncitation
    When she forgets the Charm Solar.AilmentRectifyingMethod
    Then she does not know the Charm Solar.PlagueBanishingIncitation