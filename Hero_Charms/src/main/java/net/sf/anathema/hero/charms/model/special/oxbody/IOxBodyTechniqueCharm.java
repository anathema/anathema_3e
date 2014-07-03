package net.sf.anathema.hero.charms.model.special.oxbody;

import net.sf.anathema.hero.charms.model.special.ISpecialCharm;
import net.sf.anathema.hero.health.model.HealthLevelType;
import net.sf.anathema.hero.traits.model.TraitType;

import java.util.Map;

public interface IOxBodyTechniqueCharm extends ISpecialCharm {

  TraitType[] getRelevantTraits();

  Map<String, HealthLevelType[]> getHealthLevels();
}