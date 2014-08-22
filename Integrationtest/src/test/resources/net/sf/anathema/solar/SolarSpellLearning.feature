@Integration
Feature: Characters can learn Sorcery circles
  'Terrestrial.CirrusSkiff' is a Terrestrial Circle spell
  'Celestial.CantataOfEmptyVoices' is a Celestial Circle spell
  'Solar.BenedictionOfArchgenesis' is a Solar Circle spell
  'Solar.TerrestrialCircleSorcery' is a Solar Occult 3/Essence 1 Root Charm
  'Solar.CelestialCircleSorcery' is a Solar Occult charm that needs Occult 4, Essence 2 and 'Solar.TerrestrialCircleSorcery'
  'Solar.SolarCircleSorcery' is a Solar Occult charm that needs Occult 5, Essence 3 and 'Solar.CelestialCircleSorcery'

  Scenario: Spell is not learnable if not initiated
    Given any Solar
    Then she can not learn the Spell Terrestrial.CirrusSkiff
    
  Scenario: Terrestrial Circle spell can be learned once initiated
    Given any Solar
    When I set her Occult to 5
    And she learns the Charm Solar.TerrestrialCircleSorcery
    Then she can learn the Spell Terrestrial.CirrusSkiff
    
  Scenario: Celestial Circle spell cannot be learned with Terrestrial Initiation
    Given any Solar
    When I set her Occult to 5
    And she learns the Charm Solar.TerrestrialCircleSorcery
    Then she can not learn the Spell Celestial.CantataOfEmptyVoices