package net.sf.anathema.hero.charms.model.context;

import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.hero.charms.model.learn.BasicLearningModel;
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
  public boolean isUnlearnable(BasicLearningModel group, Charm charm) {
    return strategy.isUnlearnable(group, charm);
  }

  @Override
  public boolean isLearned(BasicLearningModel group, Charm charm) {
    return strategy.isLearned(group, charm);
  }

  @Override
  public void toggleLearned(BasicLearningModel group, Charm charm) {
    strategy.toggleLearned(group, charm);
  }
}