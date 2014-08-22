@Integration
Feature: Characters can learn specialties correctly

  Scenario: A Mortal cannot learn a specialty without a rating of 1+
    Given any Mortal
    Then she can not learn a Specialty in Archery
    
  Scenario: A Mortal can learn a specialty with a rating of 1+
    Given any Mortal
    When I set her Archery to 1
    Then she can learn a Specialty in Archery
    
  Scenario: A Mortal can forget specialties during creation
    Given any Mortal
    When I set her Archery to 1
    And she learns a Specialty in Archery
    And she forgets a Specialty in Archery
    Then she knows 0 Specialties in Archery
    
  Scenario: A Mortal can learn multiple specialties with a rating of 1+
    Given any Mortal
    When I set her Archery to 1
    And she learns a Specialty in Archery
    And she learns a Specialty in Archery
    And she learns a Specialty in Archery
    Then she knows 3 Specialties in Archery
    
  Scenario: A Mortal can learn a Specialty in an ability for 3 experience points
    Given any Mortal
    When I set her Archery to 1
    And she goes experienced
    And she learns a Specialty in Archery
    Then she has spent 3 xp points
    
  Scenario: A Mortal can learn Specialties at both Creation and XP
    Given any Mortal
    When I set her Archery to 1
    And she learns a Specialty in Archery
    And she goes experienced
    And she learns a Specialty in Archery
    Then she knows 2 Specialties in Archery
    
  Scenario: A Mortal cannot forget a Specialty learned during creation once in XP
    Given any Mortal
    When I set her Archery to 1
    And she learns a Specialty in Archery
    And she goes experienced
    And she forgets a Specialty in Archery
    Then she knows 1 Specialties in Archery