package net.sf.anathema.hero.flaws.model;

import net.sf.anathema.hero.individual.model.HeroModel;
import net.sf.anathema.library.identifier.Identifier;
import net.sf.anathema.library.identifier.SimpleIdentifier;
import net.sf.anathema.library.model.property.OptionalPropertiesModel;

public interface FlawsModel extends HeroModel,
	OptionalPropertiesModel<FlawOption, Flaw> {

  Identifier ID = new SimpleIdentifier("Flaws");
}