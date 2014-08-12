package net.sf.anathema.hero.traits.model.state;

import net.sf.anathema.library.identifier.Identifier;

public interface TraitStateType extends Identifier {
  boolean countsAs(TraitStateType otherState);
}