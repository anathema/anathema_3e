package net.sf.anathema.hero.traits.model.state;

import net.sf.anathema.library.identifier.Identifier;

public enum TraitState implements Identifier {

  Default, Favored, Caste, Supernal;

  @Override
  public String getId() {
    return name();
  }
  
  public boolean countsAs(TraitState otherState) {
	  return ((this == TraitState.Caste || this == TraitState.Supernal) &&
			  (otherState == TraitState.Caste || otherState == TraitState.Supernal)) ||
			   this == otherState;
  }
}