@Integration
Feature: Characters can learn their native Charm
  'Ailment-Rectifying Method' is a Solar Medicine 1/Essence 1 Root Charm.
  'Plague-Banishing Incitation' is a Solar Medicine charm that needs Medicine 3, Essence 1 and 'Ailment-Rectifying Method'

  Scenario: Charm is not learnable if prerequisites are not met
    Given any Solar
    When I set her Medicine to 0
    Then she can not learn the Charm Ailment-Rectifying Method

  Scenario: Charm is learnable if prerequisites are met
    Given any Solar
    When I set her Medicine to 1
    Then she can learn the Charm Ailment-Rectifying Method

  Scenario: Parent Charms are automatically learned
    Given any Solar
    And I set her Medicine to 3
    When she learns the Charm Plague-Banishing Incitation
    Then she knows the Charm Ailment-Rectifying Method

  Scenario: Child Charms are automatically forgotten
    Given any Solar
    And I set her Medicine to 3
    And she learns the Charm Plague-Banishing Incitation
    When she forgets the Charm Ailment-Rectifying Method
    Then she does not know the Charm Plague-Banishing Incitation