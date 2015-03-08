package net.sf.anathema.hero.spells.sheet.magicreport;

import net.sf.anathema.hero.charms.model.CharmsModel;
import net.sf.anathema.hero.charms.model.CharmsModelFetcher;
import net.sf.anathema.hero.charms.model.learn.Charms;
import net.sf.anathema.hero.individual.model.Hero;

public class CharmFetcher {
  
  public Charms getCharms(Hero hero){
    CharmsModel charmsModel = CharmsModelFetcher.fetch(hero);
    if (charmsModel == CharmsModelFetcher.NO_MODEL) {
      return new Charms();
    }
    return charmsModel.getLearningModel().getCurrentlyLearnedCharms();
  }
  
  public boolean hasCharms(Hero hero){
    return getCharms(hero).hasCharms();
  }
}