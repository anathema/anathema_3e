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
    
  Scenario: Merits cost one free pick per dot
    Given any Mortal
    When she earns the Contacts merit at 3
    Then she has spent 3 merit points
  
  Scenario: Merits cost 1 BP per dot
  	Given any Mortal
  	When she earns the Resources merit at 5
  	And she earns the Contacts merit at 5
  	Then she has spent 3 bonus points
  	
  Scenario: Merits cost 3 xp for the first dot
  	Given any Mortal
  	When she goes experienced
  	And she earns the Language merit
  	Then she has spent 3 xp points
  	
  Scenario: Merits cost 3 xp * new rating, dead levels are free
  	Given any Mortal
  	When she goes experienced
  	And she earns the Fleet of Foot merit
  	Then she has spent 12 xp points
  	
  Scenario: Purchasing story merits does not cost xp
  	Given any Mortal
  	When she goes experienced
  	And she earns the Resources merit
  	Then she has spent 0 xp points
  	
  Scenario: Advancing story merits does not cost xp
  	Given any Mortal
  	When she earns the Resources merit
  	And she goes experienced
  	And she earns the Resources merit at 2
  	Then she has spent 0 xp points