package net.sf.anathema.hero.combos.model.rules;

import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.charm.data.CharmType;
import net.sf.anathema.charm.data.CharmTypeVisitor;
import net.sf.anathema.hero.combos.display.presenter.Combo;
import net.sf.anathema.hero.combos.model.ComboRules;

public abstract class AbstractComboArbitrator implements net.sf.anathema.hero.combos.model.ComboArbitrator {

  private final ComboRules simpleCharmRules = new SimpleCharmComboRules();
  private final ComboRules supplementalCharmRules = new SupplementalCharmComboRules();
  private final ComboRules reflexiveCharmRules = new ReflexiveCharmComboRules();

  @Override
  public void setCrossPrerequisiteTypeComboAllowed(boolean allowed) {
    simpleCharmRules.setCrossPrerequisiteTypeComboAllowed(allowed);
    supplementalCharmRules.setCrossPrerequisiteTypeComboAllowed(allowed);
    reflexiveCharmRules.setCrossPrerequisiteTypeComboAllowed(allowed);
  }

  public boolean isCharmComboLegal(Charm charm) {
    return isCharmLegalByRules(charm);
  }

  protected abstract boolean isCharmLegalByRules(Charm charm);

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
    final boolean[] legal = new boolean[1];
    charm1.getCharmType().accept(new CharmTypeVisitor() {
      @Override
      public void visitSimple(CharmType visitedType) {
        legal[0] = simpleCharmRules.isComboLegal(charm1, charm2);
      }

      @Override
      public void visitReflexive(CharmType visitedType) {
        legal[0] = reflexiveCharmRules.isComboLegal(charm1, charm2);
      }

      @Override
      public void visitSupplemental(CharmType visitedType) {
        legal[0] = supplementalCharmRules.isComboLegal(charm1, charm2);
      }

      @Override
      public void visitPermanent(CharmType visitedType) {
        legal[0] = false;
      }
    });
    return legal[0];
  }
}