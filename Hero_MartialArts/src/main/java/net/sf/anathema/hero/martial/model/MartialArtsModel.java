package net.sf.anathema.hero.martial.model;

import net.sf.anathema.hero.individual.model.HeroModel;
import net.sf.anathema.hero.traits.model.Trait;
import net.sf.anathema.hero.traits.model.TraitMap;
import net.sf.anathema.library.event.ChangeListener;
import net.sf.anathema.library.identifier.Identifier;
import net.sf.anathema.library.identifier.SimpleIdentifier;

import java.util.List;

public interface MartialArtsModel extends HeroModel {
  Identifier ID = new SimpleIdentifier("MartialArts");

  List<Trait> getAvailableStyles();

  List<Trait> getLearnedStyles();

  void selectStyle(Trait newValue);

  void selectStyle(StyleName styleName);

  Trait getSelectedStyle();

  void learnSelectedStyle();

  void forget(Trait style);

  void whenStyleIsSelected(ChangeListener listener);

  void whenStyleIsLearned(StyleLearnListener listener);

  void whenStyleIsForgotten(StyleForgetListener listener);

  void whenCharacterBecomesAMartialArtist(ChangeListener listener);

  void whenCharacterNoLongerIsAMartialArtist(ChangeListener listener);
}