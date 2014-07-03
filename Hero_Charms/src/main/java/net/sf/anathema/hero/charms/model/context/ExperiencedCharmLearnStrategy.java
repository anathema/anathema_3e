package net.sf.anathema.hero.charms.model.context;

import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.hero.charms.model.BasicLearnCharmTree;
import net.sf.anathema.hero.charms.model.learn.ICharmLearnStrategy;

public class ExperiencedCharmLearnStrategy implements ICharmLearnStrategy {

  @Override
  public boolean isUnlearnable(BasicLearnCharmTree group, Charm charm) {
    return group.isLearned(charm, true);
  }

  @Override
  public boolean isLearned(BasicLearnCharmTree group, Charm charm) {
    return group.isLearned(charm, false) || group.isLearned(charm, true);
  }

  @Override
  public void toggleLearned(BasicLearnCharmTree group, Charm charm) {
    group.toggleExperienceLearnedCharm(charm);
  }
}