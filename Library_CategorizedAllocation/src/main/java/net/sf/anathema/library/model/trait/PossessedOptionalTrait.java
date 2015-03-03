package net.sf.anathema.library.model.trait;

import net.sf.anathema.hero.traits.model.Trait;
import net.sf.anathema.library.model.PossessedOptionalEntry;

public interface PossessedOptionalTrait<O extends OptionalTraitOption> extends PossessedOptionalEntry<O>, Trait {

}
