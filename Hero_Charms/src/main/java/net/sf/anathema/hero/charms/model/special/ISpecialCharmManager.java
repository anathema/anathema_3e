package net.sf.anathema.hero.charms.model.special;

import net.sf.anathema.hero.charms.model.learn.LearningCharmTree;
import net.sf.anathema.charm.data.Charm;

public interface ISpecialCharmManager {

  CharmSpecialsModel getSpecialCharmConfiguration(Charm charm);

  void registerSpecialCharmConfiguration(ISpecialCharm specialCharm, Charm charm, LearningCharmTree group);
}