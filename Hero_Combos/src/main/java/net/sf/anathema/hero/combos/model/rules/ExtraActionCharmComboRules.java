package net.sf.anathema.hero.combos.model.rules;

import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.charm.data.CharmTypeVisitor;
import net.sf.anathema.charm.data.CharmType;

public class ExtraActionCharmComboRules extends AbstractComboRules {

  private boolean crossPrerequisite;

  @Override
  public void setCrossPrerequisiteTypeComboAllowed(boolean allowed) {
    this.crossPrerequisite = allowed;
  }

  @Override
  public boolean isComboLegal(final Charm extraActionCharm, final Charm otherCharm) {
    final boolean[] legal = new boolean[1];
    otherCharm.getCharmType().accept(new CharmTypeVisitor() {
      @Override
      public void visitSimple(CharmType visitedType) {
        boolean samePrerequisite = haveSamePrerequisite(extraActionCharm, otherCharm);
        boolean attributePrerequisites = haveAttributePrerequisites(extraActionCharm, otherCharm);
        boolean abilityAttributeCombo = crossPrerequisite && isAbilityAttributeCombo(extraActionCharm, otherCharm);
        boolean noTraitPrerequisiteCombo = hasNoTraitPrerequisites(extraActionCharm);
        legal[0] = samePrerequisite || attributePrerequisites || abilityAttributeCombo ||
                   noTraitPrerequisiteCombo;
      }

      @Override
      public void visitExtraAction(CharmType visitedType) {
        legal[0] = false;
      }

      @Override
      public void visitReflexive(CharmType visitedType) {
        legal[0] = true;
      }

      @Override
      public void visitSupplemental(CharmType visitedType) {
        boolean samePrerequisite = haveSamePrerequisite(extraActionCharm, otherCharm);
        boolean attributePrerequisites = haveAttributePrerequisites(extraActionCharm, otherCharm);
        boolean abilityAttributeCombo = crossPrerequisite && isAbilityAttributeCombo(extraActionCharm, otherCharm);
        boolean noTraitPrerequisiteCombo = hasNoTraitPrerequisites(extraActionCharm);
        legal[0] = samePrerequisite || attributePrerequisites || abilityAttributeCombo ||
                   noTraitPrerequisiteCombo;
      }

      @Override
      public void visitPermanent(CharmType visitedType) {
        legal[0] = false;
      }

      @Override
      public void visitSpecial(CharmType visitedType) {
        legal[0] = false;
      }
    });
    return legal[0];
  }
}