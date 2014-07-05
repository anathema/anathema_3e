package net.sf.anathema.hero.concept.model.description;

import net.sf.anathema.hero.individual.model.HeroModel;
import net.sf.anathema.library.event.ObjectChangedListener;
import net.sf.anathema.library.identifier.SimpleIdentifier;
import net.sf.anathema.library.text.ITextualDescription;

public interface HeroDescription extends HeroModel {

  SimpleIdentifier ID = new SimpleIdentifier("Description");

  ITextualDescription getName();

  ITextualDescription getCharacterization();

  ITextualDescription getPhysicalDescription();

  ITextualDescription getEyes();

  ITextualDescription getHair();

  ITextualDescription getSex();

  ITextualDescription getSkin();

  ITextualDescription getAnima();

  ITextualDescription getNotes();

  ITextualDescription getPlayer();

  ITextualDescription getConcept();

  void addOverallChangeListener(ObjectChangedListener<String> listener);
}