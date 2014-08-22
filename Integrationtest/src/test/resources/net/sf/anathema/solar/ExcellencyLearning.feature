@Integration
Feature: Characters learn and unlearn Excellencies correctly
  'Solar.MedicineExcellency' is a Solar Medicine 1/Essence 1 Root Charm.
  'Solar.AilmentRectifyingMethod' is a Solar Essence 1/Medicine 1 Root Charm.

  Scenario: Solar does not have Excellency with zero standard trait
    Given any Solar
    When I set her Medicine to 0
    Then she does not know the Charm Solar.MedicineExcellency

  Scenario: Solar does not have an Excellency with zero Caste trait
    Given any Solar
    When I Caste her Medicine
    Then she does not know the Charm Solar.MedicineExcellency
    
  Scenario: Solar does not have Excellency with 1 dot standard trait
    Given any Solar
    When I set her Medicine to 1
    Then she does not know the Charm Solar.MedicineExcellency
    
  Scenario: Solar has Excellency with 1 dot Caste trait
    Given any Solar
    When I Caste her Medicine
    And when I set her Medicine to 1
    Then she knows the Charm Solar.MedicineExcellency
    
  Scenario: Solar has Excellency with 1 dot favored trait
    Given any Solar
    When I favor her Medicine
    When I set her Medicine to 1
    Then she knows the Charm Solar.MedicineExcellency

  Scenario: Solar learns Excellency with another Charm for standard trait
    Given any Solar
    And I set her Medicine to 1
    And she learns the Charm Solar.AilmentRectifyingMethod
    Then she knows the Charm Solar.MedicineExcellency

  Scenario: Solar learns forgets Excellency when all Charms for a standard trait are forgotten
    Given any Solar
    And I set her Medicine to 1
    And she learns the Charm Solar.AilmentRectifyingMethod
    And she forgets the Charm Solar.AilmentRectifyingMethod
    Then she does not know the Charm Solar.MedicineExcellency