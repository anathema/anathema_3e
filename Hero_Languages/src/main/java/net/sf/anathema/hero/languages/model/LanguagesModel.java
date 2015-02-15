package net.sf.anathema.hero.languages.model;

import net.sf.anathema.hero.individual.model.HeroModel;
import net.sf.anathema.hero.merits.model.MeritReference;
import net.sf.anathema.library.identifier.Identifier;
import net.sf.anathema.library.identifier.SimpleIdentifier;

public interface LanguagesModel extends HeroModel {
  Identifier ID = new SimpleIdentifier("Languages");
  MeritReference LANGUAGE_MERIT = new MeritReference("Language");
}