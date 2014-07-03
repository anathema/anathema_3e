package net.sf.anathema.hero.combos;

import net.sf.anathema.hero.combos.model.ComboRules;
import net.sf.anathema.hero.combos.model.rules.AbstractComboArbitrator;
import net.sf.anathema.hero.dummy.DummyCharmUtilities;
import net.sf.anathema.hero.magic.charm.Charm;
import net.sf.anathema.hero.magic.charm.duration.Duration;
import net.sf.anathema.hero.magic.charm.type.CharmType;
import net.sf.anathema.hero.traits.model.types.AbilityType;
import net.sf.anathema.hero.traits.model.types.AttributeType;
import net.sf.anathema.hero.traits.model.types.ValuedTraitType;

public abstract class AbstractComboRulesTestCase {

  private ComboRules rules = new AbstractComboArbitrator() {

    @Override
    protected boolean isCharmLegalByRules(Charm charm) {
      return charm.getDuration().text.equals(Duration.INSTANT);
    }
  };

  protected ComboRules getRules() {
    return rules;
  }

  protected boolean comboSameAbilityCharms(CharmType type1, CharmType type2) {
    Charm charm1 = DummyCharmUtilities.createCharm(type1, new ValuedTraitType(AbilityType.Archery, 3));
    Charm charm2 = DummyCharmUtilities.createCharm(type2, new ValuedTraitType(AbilityType.Archery, 3));
    return rules.isComboLegal(charm1, charm2);
  }

  protected boolean comboDifferentAbilityCharms(CharmType type1, CharmType type2) {
    Charm charm1 = DummyCharmUtilities.createCharm(type1, new ValuedTraitType(AbilityType.Bureaucracy, 3));
    Charm charm2 = DummyCharmUtilities.createCharm(type2, new ValuedTraitType(AbilityType.Archery, 3));
    return rules.isComboLegal(charm1, charm2);
  }

  protected boolean comboDifferentAttributeCharms(CharmType type1, CharmType type2) {
    Charm charm1 = DummyCharmUtilities.createCharm(type1, new ValuedTraitType(AttributeType.Intelligence, 3));
    Charm charm2 = DummyCharmUtilities.createCharm(type2, new ValuedTraitType(AttributeType.Charisma, 3));
    return rules.isComboLegal(charm1, charm2);
  }

  protected boolean comboAbilityAttributeCharms(CharmType type1, CharmType type2) {
    Charm charm1 = DummyCharmUtilities.createCharm(type1, new ValuedTraitType(AbilityType.Performance, 3));
    Charm charm2 = DummyCharmUtilities.createCharm(type2, new ValuedTraitType(AttributeType.Charisma, 3));
    return rules.isComboLegal(charm1, charm2);
  }
}