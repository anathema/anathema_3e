@Integration
Feature: Characters learn and unlearn Excellencies correctly
  'Solar.ExcellentSolarMedicine' is a Solar Medicine 1/Essence 1 Root Charm.
  'Solar.AilmentRectifyingMethod' is a Solar Essence 1/Medicine 1 Root Charm.

  Scenario: Solar does not have Excellency with zero standard trait
    Given any Solar with Caste Twilight
    When I set her Medicine to 0
    Then she does not know the Charm Solar.ExcellentSolarMedicine

  Scenario: Solar does not have an Excellency with zero Caste trait
    Given any Solar with Caste Twilight
    When I Caste her Medicine
    Then she does not know the Charm Solar.ExcellentSolarMedicine
    
  Scenario: Solar does not have Excellency with 1 dot standard trait
    Given any Solar with Caste Twilight
    When I set her Medicine to 1
    Then she does not know the Charm Solar.ExcellentSolarMedicine
    
  Scenario: Solar has Excellency with 1 dot Caste trait
    Given any Solar with Caste Twilight
    When I Caste her Medicine
    And I set her Medicine to 1
    Then she knows the Charm Solar.ExcellentSolarMedicine
    
  Scenario: Solar has Excellency with 1 dot favored trait
    Given any Solar with Caste Twilight
    When I favor her Medicine
    When I set her Medicine to 1
    Then she knows the Charm Solar.ExcellentSolarMedicine

  Scenario: Solar learns Excellency with another Charm for standard trait
    Given any Solar with Caste Twilight
    And I set her Medicine to 1
    And she learns the Charm Solar.AilmentRectifyingMethod
    Then she knows the Charm Solar.ExcellentSolarMedicine

  Scenario: Solar forgets Excellency when all Charms for a standard trait are forgotten
    Given any Solar with Caste Twilight
    And I set her Medicine to 1
    And she learns the Charm Solar.AilmentRectifyingMethod
    And she forgets the Charm Solar.AilmentRectifyingMethod
    Then she does not know the Charm Solar.ExcellentSolarMedicine

  Scenario: Solar keeps Excellency when all Charms for a favored trait are forgotten
    Given any Solar with Caste Twilight
    And I favor her Medicine
    And I set her Medicine to 1
    And she learns the Charm Solar.AilmentRectifyingMethod
    When she forgets the Charm Solar.AilmentRectifyingMethod
    Then she knows the Charm Solar.ExcellentSolarMedicine

  Scenario: Solar keeps Excellency when she unfavors a trait where she still knows Charms 
    Given any Solar with Caste Twilight
    And I favor her Medicine
    And I set her Medicine to 1
    And she learns the Charm Solar.AilmentRectifyingMethod
    When I set her Medicine to Default state
    Then she knows the Charm Solar.ExcellentSolarMedicine