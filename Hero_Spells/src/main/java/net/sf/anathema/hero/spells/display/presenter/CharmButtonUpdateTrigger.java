package net.sf.anathema.hero.spells.display.presenter;

import net.sf.anathema.hero.charms.model.CharmsModel;
import net.sf.anathema.hero.charms.model.CharmsModelFetcher;
import net.sf.anathema.hero.charms.model.learn.CharmLearnAdapter;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.library.event.ChangeListener;
import net.sf.anathema.magic.data.Charm;

public class CharmButtonUpdateTrigger implements ButtonUpdateTrigger {

  private final CharmsModel model;

  public CharmButtonUpdateTrigger(Hero hero) {
    this.model = CharmsModelFetcher.fetch(hero);
  }

  public void addChangeListener(ChangeListener listener) {
    if (model == null){
      return;
    }
    model.addCharmLearnListener(new CharmLearnAdapter() {
      @Override
      public void charmLearned(Charm charm) {
        listener.changeOccurred();
      }

      @Override
      public void charmForgotten(Charm charm) {
        listener.changeOccurred();
      }
    });
  }
}
