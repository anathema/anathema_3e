package net.sf.anathema.library.model.trait;

import net.sf.anathema.hero.traits.model.Trait;
import net.sf.anathema.library.model.property.PossessedOptionalProperty;

public interface PossessedOptionalTrait<O extends OptionalTraitOption> extends PossessedOptionalProperty<O>, Trait {

}
