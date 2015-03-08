package net.sf.anathema.hero.health.model;

import net.sf.anathema.magic.data.Duration;
import net.sf.anathema.hero.individual.model.HeroModel;
import net.sf.anathema.library.identifier.Identifier;
import net.sf.anathema.library.identifier.SimpleIdentifier;

public interface HealthModel extends HeroModel {

  Identifier ID = new SimpleIdentifier("Health");

  int getHealthLevelTypeCount(HealthLevelType type);

  void addHealthLevelProvider(IHealthLevelProvider provider);

  void addPainToleranceProvider(IPainToleranceProvider provider);
  
  void addHealingTypeProvider(HealingTypeProvider provider);

  int getBasicHealthLevel(HealthLevelType type);
  
  Duration getHealingTime(HealthLevelType level, HealthType damage);

  int getPainToleranceLevel();
}