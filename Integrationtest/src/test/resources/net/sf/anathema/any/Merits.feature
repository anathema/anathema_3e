@Integration
Feature: Characters can acquire merits correctly

  Scenario: A Mortal can earn merits
    Given any Mortal
    Then she can earn the Resources merit at 3
    
  Scenario: A Mortal can not earn merits they do not meet the prerequisites for
    Given any Mortal
    Then she can not earn the Boundless Endurance merit
    
  Scenario: A Mortal can earn merits they meet the prerequisites for
    Given any Mortal
    When I set her Stamina to 3
    Then she can earn the Boundless Endurance merit
    
  Scenario: A Mortal can earn merits at their legal values
    Given any Mortal
    Then she can earn the Allies merit at 1
    
  Scenario: A Mortal can not repurchase merits that are not repurchasable
    Given any Mortal
    When she earns the Ambidextrous merit
    Then she can not earn the Ambidextrous merit