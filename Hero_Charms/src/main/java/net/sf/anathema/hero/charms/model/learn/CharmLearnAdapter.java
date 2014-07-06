package net.sf.anathema.hero.charms.model.learn;

import net.sf.anathema.charm.data.Charm;

public class CharmLearnAdapter implements ICharmLearnListener {

  @Override
  public void charmLearned(Charm charm) {
    // Nothing to do
  }

  @Override
  public void charmForgotten(Charm charm) {
    // Nothing to do
  }

  @Override
  public void charmNotLearnable(Charm charm) {
    // Nothing to do
  }

  @Override
  public void charmNotForgettable(Charm charm) {
    // Nothing to do
  }

  @Override
  public void recalculateRequested() {
    // Nothing to do    
  }
}