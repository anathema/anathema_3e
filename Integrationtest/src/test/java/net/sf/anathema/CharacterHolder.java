package net.sf.anathema;

import com.google.inject.Singleton;
import net.sf.anathema.hero.application.item.HeroItem;
import net.sf.anathema.hero.charms.model.CharmsModel;
import net.sf.anathema.hero.charms.model.CharmsModelFetcher;
import net.sf.anathema.hero.concept.HeroConcept;
import net.sf.anathema.hero.concept.HeroConceptFetcher;
import net.sf.anathema.hero.traits.model.TraitMap;
import net.sf.anathema.hero.traits.model.TraitModelFetcher;

@Singleton
public class CharacterHolder {
  private HeroItem heroItem;

  public void setCharacter(HeroItem heroItem) {
    this.heroItem = heroItem;
  }

  public CharmsModel getCharms() {
    return CharmsModelFetcher.fetch(heroItem);
  }

  public HeroConcept getCharacterConcept() {
    return HeroConceptFetcher.fetch(heroItem);
  }

  public TraitMap getTraitConfiguration() {
    return TraitModelFetcher.fetch(heroItem);
  }

  public HeroItem getHero() {
    return heroItem;
  }
}