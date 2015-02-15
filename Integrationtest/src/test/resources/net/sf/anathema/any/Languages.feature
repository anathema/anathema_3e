Feature: Characters can acquire languages correctly

  Scenario: A Mortal cannot normally learn Old Realm
    Given any Mortal
    Then she can not learn the Language of OldRealm
    
  Scenario: A Mortal who is educated can normally learn Old Realm
    Given any Mortal
    When I set her Occult to 1
    Then she can learn the Language of OldRealm
    
  Scenario: The first language is free
    Given any Mortal
    When she learns any language
    Then she has spent 0 merit points