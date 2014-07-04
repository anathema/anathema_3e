package net.sf.anathema.hero.concept;

import net.sf.anathema.hero.framework.IIntegerDescription;
import net.sf.anathema.hero.individual.model.HeroModel;
import net.sf.anathema.library.identifier.Identifier;
import net.sf.anathema.library.identifier.SimpleIdentifier;

public interface HeroConcept extends HeroModel {

  public static final Identifier ID = new SimpleIdentifier("Concept");

  CasteSelection getCaste();

  CasteCollection getCasteCollection();

  IIntegerDescription getAge();
}