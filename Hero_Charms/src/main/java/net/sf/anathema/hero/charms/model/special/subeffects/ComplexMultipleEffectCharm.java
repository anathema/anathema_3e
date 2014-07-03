package net.sf.anathema.hero.charms.model.special.subeffects;

import net.sf.anathema.charm.data.reference.CharmName;
import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.hero.charms.model.learn.CharmLearnableArbitrator;
import net.sf.anathema.hero.charms.model.special.CharmSpecialist;
import net.sf.anathema.hero.charms.model.special.upgradable.CollectionSubEffects;
import net.sf.anathema.hero.charms.model.special.prerequisite.PrerequisiteLearnCondition;
import net.sf.anathema.lib.data.Condition;

import java.util.Map;

public class ComplexMultipleEffectCharm extends MultipleEffectCharm {
  Map<String, String> prereqEffectMap;

  public ComplexMultipleEffectCharm(CharmName charmId, String[] effectIds, Map<String, String> prereqEffect) {
    super(charmId, effectIds);
    prereqEffectMap = prereqEffect;
  }

  @Override
  public SubEffects buildSubEffects(CharmSpecialist specialist, CharmLearnableArbitrator arbitrator, Charm charm) {
    CollectionSubEffects subEffects = new CollectionSubEffects();
    for (String id : effectIds) {
      String prerequisiteEffect = prereqEffectMap.get(id);
      Condition condition = buildLearnCondition(arbitrator, charm, prerequisiteEffect, subEffects);
      subEffects.add(new SubEffectImpl(id, specialist.getExperience(), condition));
    }
    return subEffects;
  }

  private Condition buildLearnCondition(CharmLearnableArbitrator arbitrator, Charm charm, String prereqEffect, CollectionSubEffects subEffects) {
    return new PrerequisiteLearnCondition(subEffects, arbitrator, charm, prereqEffect);
  }
}