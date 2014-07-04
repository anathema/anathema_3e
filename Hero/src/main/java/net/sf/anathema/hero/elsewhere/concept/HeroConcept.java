package net.sf.anathema.hero.elsewhere.concept;

import net.sf.anathema.hero.individual.model.HeroModel;
import net.sf.anathema.library.identifier.Identifier;
import net.sf.anathema.library.identifier.SimpleIdentifier;
import net.sf.anathema.library.model.IntegerModel;

public interface HeroConcept extends HeroModel {

  public static final Identifier ID = new SimpleIdentifier("Concept");

  CasteSelection getCaste();

  CasteCollection getCasteCollection();

  IntegerModel getAge();
}