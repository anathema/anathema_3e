package net.sf.anathema.hero.charms.model.context;

import net.sf.anathema.hero.charms.model.BasicLearnCharmTree;
import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.hero.charms.model.learn.ICharmLearnStrategy;

public class ProxyCharmLearnStrategy implements ICharmLearnStrategy {

  private ICharmLearnStrategy strategy;

  public ProxyCharmLearnStrategy(ICharmLearnStrategy strategy) {
    this.strategy = strategy;
  }

  public void setStrategy(ICharmLearnStrategy strategy) {
    this.strategy = strategy;
  }

  @Override
  public boolean isUnlearnable(BasicLearnCharmTree group, Charm charm) {
    return strategy.isUnlearnable(group, charm);
  }

  @Override
  public boolean isLearned(BasicLearnCharmTree group, Charm charm) {
    return strategy.isLearned(group, charm);
  }

  @Override
  public void toggleLearned(BasicLearnCharmTree group, Charm charm) {
    strategy.toggleLearned(group, charm);
  }
}