package net.sf.anathema.hero.health.model.merit;

import net.sf.anathema.hero.health.model.HealingTypeProvider;
import net.sf.anathema.hero.health.model.HealthLevelType;
import net.sf.anathema.hero.health.model.IHealthLevelProvider;
import net.sf.anathema.hero.health.model.IPainToleranceProvider;
import net.sf.anathema.hero.merits.model.MechanicalDetailReference;
import net.sf.anathema.hero.merits.model.Merit;
import net.sf.anathema.hero.merits.model.MeritsModel;
import net.sf.anathema.hero.merits.model.mechanics.MechanicalDetail;

public class MeritHealthProvider implements IHealthLevelProvider, IPainToleranceProvider, HealingTypeProvider {

  private final MeritsModel merits;

  public MeritHealthProvider(MeritsModel merits) {
    this.merits = merits;
  }

  @Override
  public int getHealthLevelTypeCount(HealthLevelType type) {
    int addedLevels = 0;
    for (Merit merit : merits.getPossessedEntries()) {
      MechanicalDetailReference addsHealthLevels = new MechanicalDetailReference("AddsHealthLevels");
      if (!(merit.hasMechanicalDetail(addsHealthLevels))) {
        continue;
      }
      MechanicalDetail detail = merit.getMechanicalDetail(addsHealthLevels);
      addedLevels += new HealthLevelDetail(detail).getLevelsOfType(type);
    }
    return addedLevels;
  }

  @Override
  public int getPainToleranceLevel() {
    int tolerance = 0;

    for (Merit merit : merits.getPossessedEntries()) {
      MechanicalDetailReference addsPainTolerance = new MechanicalDetailReference("addsPainTolerance");
      if (!(merit.hasMechanicalDetail(addsPainTolerance))){
        continue;
      }
      MechanicalDetail detail = merit.getMechanicalDetail(addsPainTolerance);
      tolerance += new PainToleranceDetail(detail).getTolerance();
    }
    return tolerance;
  }

  @Override
  public boolean usesExaltedHealing() {
    for (Merit merit : merits.getPossessedEntries()) {
      if (merit.hasMechanicalDetail(new MechanicalDetailReference("AddsExaltedHealing"))) {
        return true;
      }
    }
    return false;
  }
}