@Integration
Feature: Characters can acquire languages correctly

  Scenario: A Mortal can earn languages
    Given any Mortal
    Then she can learn the Language of HighRealm
    
  Scenario: A Mortal can not earn more languages than their limit
    Given any Mortal
    When she learns the Language of HighRealm
    Then she can not learn the Language of LowRealm
    
  Scenario: A Mortal gets more picks from the Language merit
    Given any Mortal
    When she earns the Language merit
    Then she has 2 Language picks
    
  Scenario: A Mortal has their Language pick quantity honored
    Given any Mortal
    When she learns the Language of HighRealm
    And she earns the Language merit
    Then she can learn the Language of LowRealm
    
  Scenario: A Mortal cannot normally learn Old Realm
    Given any Mortal
    Then she can not learn the Language of OldRealm
    
  Scenario: A Mortal who is educated can normally learn Old Realm
    Given any Mortal
    When I set her Occult to 1
    Then she can learn the Language of OldRealm