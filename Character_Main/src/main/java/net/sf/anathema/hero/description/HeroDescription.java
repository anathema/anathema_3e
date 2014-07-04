package net.sf.anathema.hero.description;

import net.sf.anathema.hero.model.HeroModel;
import net.sf.anathema.lib.util.SimpleIdentifier;
import net.sf.anathema.lib.workflow.textualdescription.ITextualDescription;
import net.sf.anathema.library.event.ObjectChangedListener;

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