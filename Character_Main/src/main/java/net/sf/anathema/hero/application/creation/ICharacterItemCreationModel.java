package net.sf.anathema.hero.application.creation;

import net.sf.anathema.hero.individual.splat.HeroSplat;
import net.sf.anathema.hero.individual.splat.HeroType;

import java.util.List;

public interface ICharacterItemCreationModel {

  List<HeroType> getAvailableHeroTypes();

  List<HeroSplat> getAvailableTemplates(HeroType type);
}