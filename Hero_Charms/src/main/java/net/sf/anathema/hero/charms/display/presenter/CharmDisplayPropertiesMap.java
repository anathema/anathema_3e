package net.sf.anathema.hero.charms.display.presenter;

import net.sf.anathema.hero.environment.herotype.HeroTypeSpecificsMap;
import net.sf.anathema.hero.individual.splat.HeroType;
import net.sf.anathema.library.initialization.ObjectFactory;
import net.sf.anathema.platform.tree.document.visualizer.TreePresentationProperties;

public class CharmDisplayPropertiesMap {
  private final HeroTypeSpecificsMap<CharmPresentationProperties> map;

  public CharmDisplayPropertiesMap(ObjectFactory objectFactory) {
    this.map = new HeroTypeSpecificsMap<>(objectFactory, CharmPresentationProperties.class, new NullCharmPresentationProperties());
  }

  public TreePresentationProperties getDisplayProperties(HeroType heroType) {
    return map.getForCharacterType(heroType);
  }
}