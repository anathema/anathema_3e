package net.sf.anathema.hero.combos;

import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.charm.data.CharmType;
import net.sf.anathema.charm.data.prerequisite.TraitPrerequisite;
import net.sf.anathema.hero.charms.dummy.DummyCharm;
import net.sf.anathema.hero.combos.model.ComboRules;
import net.sf.anathema.hero.combos.model.rules.DefaultComboArbitrator;
import net.sf.anathema.hero.traits.model.types.AbilityType;
import net.sf.anathema.hero.traits.model.types.AttributeType;

public abstract class AbstractComboRulesTestCase {

  private ComboRules rules = new DefaultComboArbitrator() {

  };

  protected ComboRules getRules() {
    return rules;
  }

  protected boolean comboSameAbilityCharms(CharmType type1, CharmType type2) {
    Charm charm1 = new DummyCharm("Instant", type1, TraitPrerequisite.Create(AbilityType.Archery.getId(), 3));
    Charm charm2 = new DummyCharm("Instant", type2, TraitPrerequisite.Create(AbilityType.Archery.getId(), 3));
    return rules.isComboLegal(charm1, charm2);
  }

  protected boolean comboDifferentAbilityCharms(CharmType type1, CharmType type2) {
    Charm charm1 = new DummyCharm("Instant", type1, TraitPrerequisite.Create(AbilityType.Bureaucracy.getId(), 3));
    Charm charm2 = new DummyCharm("Instant", type2, TraitPrerequisite.Create(AbilityType.Archery.getId(), 3));
    return rules.isComboLegal(charm1, charm2);
  }

  protected boolean comboDifferentAttributeCharms(CharmType type1, CharmType type2) {
    Charm charm1 = new DummyCharm("Instant", type1, TraitPrerequisite.Create(AttributeType.Intelligence.getId(), 3));
    Charm charm2 = new DummyCharm("Instant", type2, TraitPrerequisite.Create(AttributeType.Charisma.getId(), 3));
    return rules.isComboLegal(charm1, charm2);
  }

  protected boolean comboAbilityAttributeCharms(CharmType type1, CharmType type2) {
    Charm charm1 = new DummyCharm("Instant", type1,TraitPrerequisite.Create(AbilityType.Performance.getId(), 3));
    Charm charm2 = new DummyCharm("Instant", type2, TraitPrerequisite.Create(AttributeType.Charisma.getId(), 3));
    return rules.isComboLegal(charm1, charm2);
  }
}