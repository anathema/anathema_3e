package net.sf.anathema.hero.combos.model.rules;

import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.charm.data.CharmType;
import net.sf.anathema.hero.combos.display.presenter.Combo;
import net.sf.anathema.hero.combos.model.ComboRules;

public class DefaultComboArbitrator implements net.sf.anathema.hero.combos.model.ComboArbitrator {

  private final ComboRules simpleCharmRules = new SimpleCharmComboRules();
  private final ComboRules supplementalCharmRules = new SupplementalCharmComboRules();
  private final ComboRules reflexiveCharmRules = new ReflexiveCharmComboRules();

  @Override
  public void setCrossPrerequisiteTypeComboAllowed(boolean allowed) {
    simpleCharmRules.setCrossPrerequisiteTypeComboAllowed(allowed);
    supplementalCharmRules.setCrossPrerequisiteTypeComboAllowed(allowed);
    reflexiveCharmRules.setCrossPrerequisiteTypeComboAllowed(allowed);
  }

  @Override
  public boolean canBeAddedToCombo(Combo combo, Charm charm) {
    boolean legal = isCharmComboLegal(charm);
    for (Charm comboCharm : combo.getCharms()) {
      legal = legal && isComboLegal(comboCharm, charm);
    }
    return legal;
  }

  @SuppressWarnings("SimplifiableIfStatement")
  @Override
  public boolean isComboLegal(Charm charm1, Charm charm2) {
    if (charm1 == charm2) {
      return false;
    }
    if (!isCharmComboLegal(charm1) || !isCharmComboLegal(charm2)) {
      return false;
    }
    return handleComboRules(charm1, charm2) && handleComboRules(charm2, charm1);
  }

  private boolean handleComboRules(final Charm charm1, final Charm charm2) {
    CharmType charmType = charm1.getCharmType();
    switch (charmType) {
      case Permanent:
        return false;
      case Simple:
        return simpleCharmRules.isComboLegal(charm1, charm2);
      case Reflexive:
        return reflexiveCharmRules.isComboLegal(charm1, charm2);
      case Supplemental:
        return supplementalCharmRules.isComboLegal(charm1, charm2);
      default:
        throw new IllegalArgumentException("Unknown Charm type " + charmType);
    }
  }

  private boolean isCharmComboLegal(Charm charm) {
    return charm.getCharmType() != CharmType.Permanent;
  }
}