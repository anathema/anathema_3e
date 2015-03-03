package net.sf.anathema.hero.charms.persistence.special;

import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.charm.data.reference.CharmName;
import net.sf.anathema.hero.charms.model.CharmMap;
import net.sf.anathema.hero.charms.model.CharmsModel;
import net.sf.anathema.hero.charms.model.special.CharmSpecialLearning;
import net.sf.anathema.hero.charms.model.special.CharmSpecialLearningModel;
import net.sf.anathema.hero.charms.model.special.ICharmSpecialLearningVisitor;
import net.sf.anathema.hero.charms.model.special.learning.multilearn.IMultiLearnableCharm;
import net.sf.anathema.hero.charms.model.special.learning.multilearn.MultiLearnCharmSpecials;
import net.sf.anathema.hero.charms.model.special.subeffects.IMultipleEffectCharm;
import net.sf.anathema.hero.charms.model.special.subeffects.ISubEffectCharm;
import net.sf.anathema.hero.charms.persistence.CharmListPto;
import net.sf.anathema.hero.charms.persistence.special.effect.MultipleEffectCharmPersister;
import net.sf.anathema.hero.charms.persistence.special.learn.MultiLearnCharmPersister;

import java.util.HashMap;
import java.util.Map;

public class SpecialCharmListPersister {

  private final Map<Charm, SpecialCharmPersister> persisterByCharm = new HashMap<>();

  public SpecialCharmListPersister(CharmsModel model) {
    final CharmMap charmTree = model.getOptions().getCharmIdMap();
    for (CharmSpecialLearning specialCharm : model.getOptions().getSpecialLearningCharms()) {
      specialCharm.accept(new ICharmSpecialLearningVisitor() {
        @Override
        public void visitMultiLearnableCharm(IMultiLearnableCharm charm) {
          persisterByCharm.put(getCharm(charm.getCharmName(), charmTree), new MultiLearnCharmPersister());
        }

        @Override
        public void visitSubEffectCharm(ISubEffectCharm charm) {
          persisterByCharm.put(getCharm(charm.getCharmName(), charmTree), new MultipleEffectCharmPersister());
        }

        @Override
        public void visitMultipleEffectCharm(IMultipleEffectCharm charm) {
          persisterByCharm.put(getCharm(charm.getCharmName(), charmTree), new MultipleEffectCharmPersister());
        }
      });
    }
  }

  private Charm getCharm(CharmName charmId, CharmMap charmTree) {
    return charmTree.getCharmById(charmId);
  }

  public void saveCharmSpecials(CharmsModel charmsModel, Charm charm, CharmListPto charmPto) {
    CharmSpecialLearningModel charmSpecials = charmsModel.getCharmSpecialLearningModel(charm);
    SpecialCharmPersister specialCharmPersister = persisterByCharm.get(charm);
    if (charmSpecials == null || specialCharmPersister == null) {
      return;
    }
    CharmSpecialsPto specialPto = new CharmSpecialsPto();
    specialPto.charmId = charm.getName().text;
    specialCharmPersister.saveCharmSpecials(charmSpecials, specialPto);
    charmPto.charmSpecials.add(specialPto);
  }

  public void loadSpecials(CharmsModel model, Charm charm, CharmListPto pto, boolean isExperienceLearned) {
    SpecialCharmPersister specialPersister = persisterByCharm.get(charm);
    CharmSpecialLearningModel charmSpecials = model.getCharmSpecialLearningModel(charm);
    CharmSpecialsPto charmSpecialsPto = getSpecialCharmPto(charm.getName().text, pto);
    if (charmSpecialsPto != null && charmSpecials != null) {
      specialPersister.loadCharmSpecials(charmSpecials, charmSpecialsPto);
    } else if (charmSpecials instanceof MultiLearnCharmSpecials) {
      charmSpecials.learn(isExperienceLearned);
    }
  }

  private CharmSpecialsPto getSpecialCharmPto(String charmId, CharmListPto pto) {
    for (CharmSpecialsPto specialsPto : pto.charmSpecials) {
      if (specialsPto.charmId.equals(charmId)) {
        return specialsPto;
      }
    }
    return null;
  }
}
