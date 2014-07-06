package net.sf.anathema.hero.application.creation;

import net.sf.anathema.hero.individual.splat.HeroSplat;
import net.sf.anathema.hero.individual.splat.HeroType;
import net.sf.anathema.library.event.ChangeListener;

import java.util.Collection;
import java.util.List;

public interface ICharacterItemCreationModel {

  void setCharacterType(HeroType type);

  void setSelectedTemplate(HeroSplat newValue);

  void addListener(ChangeListener listener);

  Iterable<HeroType> getAvailableHeroTypes();

  List<HeroSplat> getAvailableTemplates();

  HeroSplat getSelectedTemplate();
}