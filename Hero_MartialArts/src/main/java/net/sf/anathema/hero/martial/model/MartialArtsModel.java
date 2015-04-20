package net.sf.anathema.hero.martial.model;

import net.sf.anathema.hero.individual.model.HeroModel;
import net.sf.anathema.library.event.ChangeListener;
import net.sf.anathema.library.identifier.Identifier;
import net.sf.anathema.library.identifier.SimpleIdentifier;

import java.util.Collection;
import java.util.List;

public interface MartialArtsModel extends HeroModel {
  Identifier ID = new SimpleIdentifier("MartialArts");

  List<StyleName> getAllStyles();

  void selectStyle(StyleName newValue);

  void whenStyleIsSelected(ChangeListener listener);

  StyleName getSelectedStyle();

  void learnSelectedStyle();
}