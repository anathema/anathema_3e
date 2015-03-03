package net.sf.anathema.hero.thaumaturgy.model;

import net.sf.anathema.hero.individual.model.HeroModel;
import net.sf.anathema.library.identifier.Identifier;
import net.sf.anathema.library.identifier.SimpleIdentifier;
import net.sf.anathema.library.model.RemovableEntryModel;
import net.sf.anathema.library.model.trait.OptionalTraitsModel;

public interface ThaumaturgyModel extends RemovableEntryModel<KnownRitual>,
	HeroModel,
	OptionalTraitsModel<ThaumaturgyRitual, KnownRitual> {

  Identifier ID = new SimpleIdentifier("Thaumaturgy");
  
  void addThaumaturgyProvider(ThaumaturgyProvider provider);
  
  int getFreeRitualPicks();
}