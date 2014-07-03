package net.sf.anathema.hero.combos.model.rules;

import net.sf.anathema.hero.magic.charm.Charm;
import net.sf.anathema.hero.magic.charm.type.CharmTypeVisitor;
import net.sf.anathema.hero.magic.charm.type.CharmType;

public class SimpleCharmComboRules extends AbstractComboRules {
  private boolean crossPrerequisite;

  @Override
  public void setCrossPrerequisiteTypeComboAllowed(boolean allowed) {
    this.crossPrerequisite = allowed;
  }

  @Override
  public boolean isComboLegal(final Charm simpleCharm, final Charm otherCharm) {
    final boolean[] legal = new boolean[1];
    otherCharm.getCharmType().accept(new CharmTypeVisitor() {
      @Override
      public void visitSimple(CharmType visitedType) {
        legal[0] = false;
      }

      @Override
      public void visitExtraAction(CharmType visitedType) {
        boolean samePrerequisite = haveSamePrerequisite(simpleCharm, otherCharm);
        boolean attributePrerequisites = haveAttributePrerequisites(simpleCharm, otherCharm);
        boolean abilityAttributeCombo = crossPrerequisite && isAbilityAttributeCombo(simpleCharm, otherCharm);
        boolean noTraitPrerequisiteCombo = hasNoTraitPrerequisites(simpleCharm);
        legal[0] = samePrerequisite || attributePrerequisites || abilityAttributeCombo ||
                   noTraitPrerequisiteCombo;
      }

      @Override
      public void visitReflexive(CharmType visitedType) {
        legal[0] = true;
      }

      @Override
      public void visitSupplemental(CharmType visitedType) {
        boolean samePrerequisite = haveSamePrerequisite(simpleCharm, otherCharm);
        boolean attributePrerequisites = haveAttributePrerequisites(simpleCharm, otherCharm);
        boolean abilityAttributeCombo = crossPrerequisite && isAbilityAttributeCombo(simpleCharm, otherCharm);
        boolean noTraitPrerequisiteCombo = hasNoTraitPrerequisites(simpleCharm);
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