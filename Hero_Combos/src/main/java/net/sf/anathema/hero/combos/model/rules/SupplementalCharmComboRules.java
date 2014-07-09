package net.sf.anathema.hero.combos.model.rules;

import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.charm.data.CharmType;

public class SupplementalCharmComboRules extends AbstractComboRules {
  private boolean crossPrerequisite;

  @Override
  public void setCrossPrerequisiteTypeComboAllowed(boolean allowed) {
    this.crossPrerequisite = allowed;
  }

  @Override
  public boolean isComboLegal(Charm supplementalCharm, Charm otherCharm) {
    CharmType otherType = otherCharm.getCharmType();
    switch (otherType) {
      case Reflexive:
        return true;
      case Permanent:
        return false;
      default:
        return haveCompatiblePrerequisites(supplementalCharm, otherCharm, crossPrerequisite);
    }
  }
}