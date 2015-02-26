package net.sf.anathema.hero.merits.model;

import java.util.Collection;
import java.util.List;

import net.sf.anathema.hero.individual.model.HeroModel;
import net.sf.anathema.library.identifier.Identifier;
import net.sf.anathema.library.identifier.SimpleIdentifier;
import net.sf.anathema.library.model.OptionalTraitReference;
import net.sf.anathema.library.model.OptionalTraitsModel;
import net.sf.anathema.library.model.RemovableEntryModel;

public interface MeritsModel extends RemovableEntryModel<Merit>, HeroModel,
	OptionalTraitsModel<MeritCategory, MeritOption, Merit>{

  Identifier ID = new SimpleIdentifier("Merits");

  boolean hasMeritsMatchingReference(OptionalTraitReference reference);

  List<Merit> getMeritsMatchingReference(OptionalTraitReference option);

  boolean isEntryAllowed();

  void addSuggestions(OptionalTraitReference merit, Collection<String> suggestions);
}