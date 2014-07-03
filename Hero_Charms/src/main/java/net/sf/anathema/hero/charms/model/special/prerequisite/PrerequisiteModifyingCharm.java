package net.sf.anathema.hero.charms.model.special.prerequisite;

import net.sf.anathema.charm.data.reference.CharmName;
import net.sf.anathema.hero.magic.charm.Charm;
import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.hero.charms.model.special.ISpecialCharmVisitor;

public class PrerequisiteModifyingCharm implements IPrerequisiteModifyingCharm {
  private final CharmName charmId;
  private final TraitType traitType;
  private final int modifier;

  public PrerequisiteModifyingCharm(CharmName charmId, TraitType traitType, int modifier) {
    this.traitType = traitType;
    this.modifier = modifier;
    this.charmId = charmId;
  }

  @Override
  public int modifyRequiredValue(Charm charm, int currentlyRequiredValue) {
    TraitType mainTrait = charm.getPrerequisites().getPrimaryTraitType();
    return modifyRequiredValueIfIsApplicableToCandidate(mainTrait, currentlyRequiredValue);
  }

  @Override
  public int modifyRequiredValueIfIsApplicableToCandidate(TraitType candidateTrait, int currentlyRequiredValue) {
    //Assuming that the limit imposed for Transcendence of Ability transfers to all other Charms.
    if (currentlyRequiredValue == 10) {
      return currentlyRequiredValue;
    }
    try {
      //Assuming modification of all traits in applicable charms by the same value.
      if (candidateTrait == traitType) {
        return currentlyRequiredValue + modifier;
      }
    } catch (Exception ignored) {
    }
    return currentlyRequiredValue;
  }

  @Override
  public void accept(ISpecialCharmVisitor visitor) {
    visitor.visitPrerequisiteModifyingCharm(this);
  }

  @Override
  public CharmName getCharmName() {
    return charmId;
  }
}