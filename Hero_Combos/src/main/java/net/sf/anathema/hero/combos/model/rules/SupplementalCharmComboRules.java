package net.sf.anathema.hero.combos.model.rules;

import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.charm.data.CharmTypeVisitor;
import net.sf.anathema.charm.data.CharmType;

public class SupplementalCharmComboRules extends AbstractComboRules {
  private boolean crossPrerequisite;

  @Override
  public void setCrossPrerequisiteTypeComboAllowed(boolean allowed) {
    this.crossPrerequisite = allowed;
  }

  @Override
  public boolean isComboLegal(final Charm supplementalCharm, final Charm otherCharm) {
    final boolean[] legal = new boolean[1];
    otherCharm.getCharmType().accept(new CharmTypeVisitor() {
      @Override
      public void visitSimple(CharmType visitedType) {
        boolean samePrerequisite = haveSamePrerequisite(supplementalCharm, otherCharm);
        boolean attributePrerequisites = haveAttributePrerequisites(supplementalCharm, otherCharm);
        boolean abilityAttributeCombo = crossPrerequisite && isAbilityAttributeCombo(supplementalCharm, otherCharm);
        boolean noTraitPrerequisiteCombo = hasNoTraitPrerequisites(supplementalCharm);
        legal[0] = samePrerequisite || attributePrerequisites || abilityAttributeCombo ||
                   noTraitPrerequisiteCombo;
      }

      @Override
      public void visitExtraAction(CharmType visitedType) {
        boolean samePrerequisite = haveSamePrerequisite(supplementalCharm, otherCharm);
        boolean attributePrerequisites = haveAttributePrerequisites(supplementalCharm, otherCharm);
        boolean abilityAttributeCombo = crossPrerequisite && isAbilityAttributeCombo(supplementalCharm, otherCharm);
        boolean noTraitPrerequisiteCombo = hasNoTraitPrerequisites(supplementalCharm);
        legal[0] = samePrerequisite || attributePrerequisites || abilityAttributeCombo ||
                   noTraitPrerequisiteCombo;
      }

      @Override
      public void visitReflexive(CharmType visitedType) {
        legal[0] = true;
      }

      @Override
      public void visitSupplemental(CharmType visitedType) {
        boolean samePrerequisite = haveSamePrerequisite(supplementalCharm, otherCharm);
        boolean attributePrerequisites = haveAttributePrerequisites(supplementalCharm, otherCharm);
        boolean abilityAttributeCombo = crossPrerequisite && isAbilityAttributeCombo(supplementalCharm, otherCharm);
        boolean noTraitPrerequisiteCombo = hasNoTraitPrerequisites(supplementalCharm);
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