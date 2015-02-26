package net.sf.anathema.hero.thaumaturgy.model;

import net.sf.anathema.hero.individual.model.HeroModel;
import net.sf.anathema.library.identifier.Identifier;
import net.sf.anathema.library.identifier.SimpleIdentifier;
import net.sf.anathema.library.model.NullCategory;
import net.sf.anathema.library.model.OptionalTraitsModel;
import net.sf.anathema.library.model.RemovableEntryModel;

public interface ThaumaturgyModel extends RemovableEntryModel<KnownRitual>,
	HeroModel,
	OptionalTraitsModel<NullCategory, ThaumaturgyRitual, KnownRitual> {

  Identifier ID = new SimpleIdentifier("Thaumaturgy");
  
  void addThaumaturgyProvider(ThaumaturgyProvider provider);
}