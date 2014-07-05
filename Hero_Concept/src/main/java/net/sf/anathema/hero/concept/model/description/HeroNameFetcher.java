package net.sf.anathema.hero.concept.model.description;

import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.library.text.ITextualDescription;

public class HeroNameFetcher {
  public static final String DEFAULT_PRINT_NAME = "Unnamed";

  public String getName(Hero hero) {
    HeroDescription heroDescription = HeroDescriptionFetcher.fetch(hero);
    if (heroDescription == HeroDescriptionFetcher.NOT_REGISTERED){
      return DEFAULT_PRINT_NAME;
    }
    ITextualDescription nameDescription = heroDescription.getName();
    if (nameDescription.isEmpty()){
      return DEFAULT_PRINT_NAME;
    }
    return nameDescription.getText();
  }
}