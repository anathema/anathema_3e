package net.sf.anathema.hero.charms.model.special;

import net.sf.anathema.magic.data.Charm;
import net.sf.anathema.hero.charms.display.special.CharmSpecialistImpl;
import net.sf.anathema.hero.charms.model.CharmsModel;
import net.sf.anathema.hero.charms.model.learn.CharmLearnAdapter;
import net.sf.anathema.hero.charms.model.learn.IExtendedCharmLearnableArbitrator;
import net.sf.anathema.hero.charms.model.learn.LearningModel;
import net.sf.anathema.hero.charms.model.special.learning.multilearn.IMultiLearnableCharm;
import net.sf.anathema.hero.charms.model.special.learning.multilearn.MultiLearnableCharmSpecialsImpl;
import net.sf.anathema.hero.charms.model.special.subeffects.IMultipleEffectCharm;
import net.sf.anathema.hero.charms.model.special.subeffects.ISubEffectCharm;
import net.sf.anathema.hero.charms.model.special.subeffects.MultipleEffectCharmSpecialsImpl;
import net.sf.anathema.hero.charms.model.special.subeffects.SubEffectCharmSpecialsImpl;
import net.sf.anathema.hero.individual.model.Hero;

import java.util.HashMap;
import java.util.Map;

public class SpecialCharmManager implements ISpecialCharmManager {
  private final Map<Charm, CharmSpecialLearningModel> specialConfigurationsByCharm = new HashMap<>();
  private final IExtendedCharmLearnableArbitrator arbitrator;
  private CharmSpecialistImpl specialist;
  private Hero hero;

  public SpecialCharmManager(CharmSpecialistImpl specialist, Hero hero, CharmsModel charmsModel) {
    this.specialist = specialist;
    this.hero = hero;
    this.arbitrator = charmsModel;
  }

  @Override
  public void registerSpecialCharmConfiguration(CharmSpecialLearning specialCharm, Charm charm, LearningModel learningModel) {
    specialCharm.accept(new ICharmSpecialLearningVisitor() {
      @Override
      public void visitMultiLearnableCharm(IMultiLearnableCharm visitedCharm) {
        registerMultiLearnableCharm(visitedCharm, charm, learningModel);
      }

      @Override
      public void visitMultipleEffectCharm(IMultipleEffectCharm visitedCharm) {
        registerEffectMultilearnableCharm(visitedCharm, charm, learningModel);
      }

      @Override
      public void visitSubEffectCharm(ISubEffectCharm visitedCharm) {
        registerSubEffectCharm(visitedCharm, charm, learningModel);
      }
    });
  }

  @Override
  public CharmSpecialLearningModel getSpecialCharmConfiguration(Charm charm) {
    return specialConfigurationsByCharm.get(charm);
  }

  private void registerEffectMultilearnableCharm(IMultipleEffectCharm visited, Charm charm, LearningModel group) {
    MultipleEffectCharmSpecialsImpl configuration = new MultipleEffectCharmSpecialsImpl(specialist, charm, visited,
            arbitrator);
    addSpecialCharmConfiguration(charm, group, configuration, true, true);
  }

  private void registerMultiLearnableCharm(IMultiLearnableCharm visitedCharm, Charm charm, LearningModel group) {
    MultiLearnableCharmSpecialsImpl configuration = new MultiLearnableCharmSpecialsImpl(hero, charm,
            visitedCharm, arbitrator);
    addSpecialCharmConfiguration(charm, group, configuration, true, true);
  }

  private void registerSubEffectCharm(ISubEffectCharm visited, Charm charm, LearningModel group) {
    SubEffectCharmSpecialsImpl configuration = new SubEffectCharmSpecialsImpl(specialist, charm, visited, arbitrator);
    addSpecialCharmConfiguration(charm, group, configuration, true, true);
  }

  private void addSpecialCharmConfiguration(Charm charm, LearningModel group, CharmSpecialLearningModel charmSpecialsModel,
                                            boolean learnListener, boolean forgetAtZero) {
    if (specialConfigurationsByCharm.containsKey(charm)) {
      throw new IllegalArgumentException("Special charmSpecialsModel already defined for charm " + charm.getName().text);
    }
    specialConfigurationsByCharm.put(charm, charmSpecialsModel);
    if (learnListener) {
      charmSpecialsModel.addSpecialCharmLearnListener(newValue -> {
        if (!hero.isFullyLoaded()) {
          return;
        }
        if (newValue == 0) {
          if (forgetAtZero) {
            group.forgetCharm(charm, group.isLearnedWithExperience(charm));
          } else {
            group.fireRecalculateRequested();
          }
        } else {
          if (!group.isCurrentlyLearned(charm)) {
            group.toggleLearned(charm);
          }
          group.fireRecalculateRequested();
        }
      });
    }
    group.addCharmLearnListener(new CharmLearnAdapter() {
      @Override
      public void charmForgotten(Charm forgottenCharm) {
        if (charm.equals(forgottenCharm)) {
          charmSpecialsModel.forget();
        }
      }

      @Override
      public void charmLearned(Charm learnedCharm) {
        if (charm.equals(learnedCharm)) {
          charmSpecialsModel.learn(group.isLearnedWithExperience(charm));
        }
      }
    });
  }
}