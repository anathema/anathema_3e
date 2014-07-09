package net.sf.anathema.hero.combos.model.rules;

import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.charm.data.CharmType;

public class SimpleCharmComboRules extends AbstractComboRules {
  private boolean crossPrerequisite;

  @Override
  public void setCrossPrerequisiteTypeComboAllowed(boolean allowed) {
    this.crossPrerequisite = allowed;
  }

  @Override
  public boolean isComboLegal(Charm simpleCharm, Charm otherCharm) {
    CharmType otherType = otherCharm.getCharmType();
    switch (otherType) {
      case Supplemental:
        return haveCompatiblePrerequisites(simpleCharm, otherCharm, crossPrerequisite);
      case Reflexive:
        return true;
      default:
        return false;
    }
  }
}