package net.sf.anathema.hero.traits.model;

import net.sf.anathema.hero.traits.model.types.ITraitTypeVisitor;
import net.sf.anathema.library.identifier.Identifier;

public interface TraitType extends Identifier {

  void accept(ITraitTypeVisitor visitor);
}