package net.sf.anathema.hero.spells.sheet.magicreport;

import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.hero.charms.model.CharmsModel;
import net.sf.anathema.hero.charms.model.CharmsModelFetcher;
import net.sf.anathema.hero.individual.model.Hero;

import java.util.HashSet;
import java.util.Set;

public class CharmFetcher {
  
  public Set<Charm> getCharms(Hero hero){
    CharmsModel charmsModel = CharmsModelFetcher.fetch(hero);
    if (charmsModel == CharmsModelFetcher.NO_MODEL) {
      return new HashSet<>();
    }
    return charmsModel.getLearningModel().getCurrentlyLearnedCharms();
  }
  
  public boolean hasCharms(Hero hero){
    return getCharms(hero).size() > 0;
  }
}