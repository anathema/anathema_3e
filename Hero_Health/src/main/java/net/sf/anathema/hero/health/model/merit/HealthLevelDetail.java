package net.sf.anathema.hero.health.model.merit;

import net.sf.anathema.hero.health.model.HealthLevelType;
import net.sf.anathema.hero.merits.model.mechanics.DetailEntryReference;
import net.sf.anathema.hero.merits.model.mechanics.MechanicalDetail;

import java.util.Collection;

import static java.util.stream.Collectors.toList;

public class HealthLevelDetail {
  private MechanicalDetail detail;

  public HealthLevelDetail(MechanicalDetail detail) {
    this.detail = detail;
  }

  public int getLevelsOfType(HealthLevelType type) {
    Collection<HealthLevelType> healthLevels = getAllProvidedHealthLevels();
    return countLevelsOfType(type, healthLevels);
  }

  private Collection<HealthLevelType> getAllProvidedHealthLevels() {
    Collection<String> healthLevelsAsString =  detail.getEntry(new DetailEntryReference("healthLevels"));
    return healthLevelsAsString.stream().map(HealthLevelType::byGameNotation).collect(toList());
  }

  private int countLevelsOfType(HealthLevelType type, Collection<HealthLevelType> healthLevels) {
    int levelsOfType = 0;
    for (HealthLevelType providedType : healthLevels) {
      if (providedType == type) {
        levelsOfType++;
      }
    }
    return levelsOfType;
  }
}
