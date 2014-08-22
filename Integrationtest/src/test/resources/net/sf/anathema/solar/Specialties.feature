@Integration
Feature: Characters can learn specialties correctly

  Scenario: A Mortal cannot learn a specialty without a rating of 1+
    Given any Mortal
    Then she can not learn a Specialty in Archery
    
  Scenario: A Mortal can learn a specialty with a rating of 1+
    Given any Mortal
    When I set her Archery to 1
    Then she can learn a Specialty in Archery
    
  Scenario: A Mortal can learn multiple specialties with a rating of 1+
    Given any Mortal
    When I set her Archery to 1
    And she learns a Specialty in Archery
    And she learns a Specialty in Archery
    And she learns a Specialty in Archery
    Then she knows 3 Specialties in Archery