package net.sf.anathema.hero.traits.model.state;

import net.sf.anathema.library.identifier.Identifier;

public enum TraitStateType implements Identifier {

  Default, Favored, Caste, Supernal;

  @Override
  public String getId() {
    return name();
  }
  
  public boolean countsAs(TraitStateType otherState) {
	  return ((this == TraitStateType.Caste || this == TraitStateType.Supernal) &&
			  (otherState == TraitStateType.Caste || otherState == TraitStateType.Supernal)) ||
			   this == otherState;
  }
}