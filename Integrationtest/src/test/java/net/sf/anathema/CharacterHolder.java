package net.sf.anathema;

import cucumber.runtime.java.guice.ScenarioScoped;

import net.sf.anathema.hero.application.item.HeroItemData;
import net.sf.anathema.hero.charms.model.CharmsModel;
import net.sf.anathema.hero.charms.model.CharmsModelFetcher;
import net.sf.anathema.hero.concept.model.concept.HeroConcept;
import net.sf.anathema.hero.concept.model.concept.HeroConceptFetcher;
import net.sf.anathema.hero.merits.model.MeritsModel;
import net.sf.anathema.hero.merits.model.MeritsModelFetcher;
import net.sf.anathema.hero.spells.model.SpellsModel;
import net.sf.anathema.hero.spells.model.SpellsModelFetcher;
import net.sf.anathema.hero.traits.model.TraitMap;
import net.sf.anathema.hero.traits.model.TraitModelFetcher;

@ScenarioScoped
public class CharacterHolder {
  private HeroItemData heroItemData;

  public void setCharacter(HeroItemData heroItemData) {
    this.heroItemData = heroItemData;
  }

  public CharmsModel getCharms() {
    return CharmsModelFetcher.fetch(heroItemData);
  }
  
  public SpellsModel getSpells() {
  	return SpellsModelFetcher.fetch(heroItemData);
  }
  
  public MeritsModel getMerits() {
  	return MeritsModelFetcher.fetch(heroItemData);
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