package net.sf.anathema.hero.combos.model.rules;

import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.charm.data.CharmType;

public class ReflexiveCharmComboRules extends AbstractComboRules {
  private boolean crossPrerequisite;

  @Override
  public void setCrossPrerequisiteTypeComboAllowed(boolean allowed) {
    this.crossPrerequisite = allowed;
  }

  @Override
  public boolean isComboLegal(final Charm reflexiveCharm, final Charm otherCharm) {
    CharmType otherType = otherCharm.getCharmType();
    switch (otherType) {
      case Permanent:
        return false;
      default:
        return haveAbilityPrerequisites(reflexiveCharm, otherCharm) || haveAttributePrerequisites(reflexiveCharm,
                otherCharm) ||
                hasNoTraitPrerequisites(reflexiveCharm) || crossPrerequisite;
    }
  }
}