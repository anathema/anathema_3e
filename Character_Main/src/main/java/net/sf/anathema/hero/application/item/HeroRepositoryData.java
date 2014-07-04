package net.sf.anathema.hero.application.item;

import net.sf.anathema.hero.description.HeroNameFetcher;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.library.lang.StringUtilities;
import net.sf.anathema.platform.item.IItemType;
import net.sf.anathema.platform.repository.RepositoryIdData;

public class HeroRepositoryData implements RepositoryIdData {

  private Hero hero;

  public HeroRepositoryData(Hero hero) {
    this.hero = hero;
  }

  @Override
  public String getIdProposal() {
    String name = new HeroNameFetcher().getName(hero);
    return StringUtilities.getFileNameRepresentation(name);
  }

  @Override
  public IItemType getItemType() {
    return HeroItemTypeRetrieval.retrieveCharacterItemType();
  }
}