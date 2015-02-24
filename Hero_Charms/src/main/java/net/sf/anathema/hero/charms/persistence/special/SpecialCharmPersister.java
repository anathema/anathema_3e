package net.sf.anathema.hero.charms.persistence.special;

import net.sf.anathema.hero.charms.model.special.CharmSpecialLearningModel;

public interface SpecialCharmPersister {

  void saveCharmSpecials(CharmSpecialLearningModel charmSpecials, CharmSpecialsPto specialsPto);

  void loadCharmSpecials(CharmSpecialLearningModel charmSpecials, CharmSpecialsPto specialsPto);
}
