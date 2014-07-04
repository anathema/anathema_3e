package net.sf.anathema.hero.individual.splat;

import java.util.List;

public interface HeroSplat {

  SplatType getTemplateType();

  List<ConfiguredModel> getModels();
}