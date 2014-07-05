package net.sf.anathema.hero.application.item;

import net.sf.anathema.hero.concept.model.description.HeroDescription;
import net.sf.anathema.hero.concept.model.description.HeroDescriptionFetcher;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.library.text.ITextualDescription;

public class HeroNameFetcher {

  public String getName(Hero hero) {
    HeroDescription heroDescription = HeroDescriptionFetcher.fetch(hero);
    if (heroDescription == HeroDescriptionFetcher.NOT_REGISTERED){
      return HeroItem.DEFAULT_PRINT_NAME;
    }
    ITextualDescription nameDescription = heroDescription.getName();
    if (nameDescription.isEmpty()){
      return HeroItem.DEFAULT_PRINT_NAME;
    }
    return nameDescription.getText();
  }
}