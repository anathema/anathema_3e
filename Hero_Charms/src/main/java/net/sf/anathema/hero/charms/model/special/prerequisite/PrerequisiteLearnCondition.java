package net.sf.anathema.hero.charms.model.special.prerequisite;

import net.sf.anathema.hero.magic.charm.Charm;
import net.sf.anathema.hero.magic.charm.CharmLearnableArbitrator;
import net.sf.anathema.hero.charms.model.special.subeffects.SubEffect;
import net.sf.anathema.hero.charms.model.special.subeffects.SubEffects;
import net.sf.anathema.lib.data.Condition;

public class PrerequisiteLearnCondition implements Condition {
  private final SubEffects allEffects;
  private final CharmLearnableArbitrator arbitrator;
  private final Charm charm;
  private final String prereqEffect;

  public PrerequisiteLearnCondition(SubEffects allEffects, CharmLearnableArbitrator arbitrator, Charm charm, String prereqEffect) {
    this.allEffects = allEffects;
    this.arbitrator = arbitrator;
    this.charm = charm;
    this.prereqEffect = prereqEffect;
  }

  @Override
  public boolean isFulfilled() {
    if (!arbitrator.isLearnable(charm)) {
      return false;
    }
    if (prereqEffect == null) {
      return true;
    }
    for (SubEffect effect : allEffects) {
      if (effect.getId().equals(prereqEffect) && effect.isLearned()) {
        return true;
      }
    }
    return false;
  }
}