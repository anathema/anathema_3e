package net.sf.anathema;

import com.google.inject.Singleton;
import net.sf.anathema.hero.application.item.HeroItemData;
import net.sf.anathema.hero.charms.model.CharmsModel;
import net.sf.anathema.hero.charms.model.CharmsModelFetcher;
import net.sf.anathema.hero.concept.model.concept.HeroConcept;
import net.sf.anathema.hero.concept.model.concept.HeroConceptFetcher;
import net.sf.anathema.hero.traits.model.TraitMap;
import net.sf.anathema.hero.traits.model.TraitModelFetcher;

@Singleton
public class CharacterHolder {
  private HeroItemData heroItemData;

  public void setCharacter(HeroItemData heroItemData) {
    this.heroItemData = heroItemData;
  }

  public CharmsModel getCharms() {
    return CharmsModelFetcher.fetch(heroItemData);
  }

  public HeroConcept getCharacterConcept() {
    return HeroConceptFetcher.fetch(heroItemData);
  }

  public TraitMap getTraitConfiguration() {
    return TraitModelFetcher.fetch(heroItemData);
  }

  public HeroItemData getHero() {
    return heroItemData;
  }
}