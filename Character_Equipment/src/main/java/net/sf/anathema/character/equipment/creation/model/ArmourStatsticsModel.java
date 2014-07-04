package net.sf.anathema.character.equipment.creation.model;

import net.sf.anathema.character.equipment.creation.presenter.IArmourStatisticsModel;
import net.sf.anathema.character.equipment.creation.presenter.IIntValueModel;
import net.sf.anathema.character.equipment.creation.presenter.RangedIntValueModel;
import net.sf.anathema.hero.health.model.HealthType;
import net.sf.anathema.hero.health.model.IHealthTypeVisitor;
import net.sf.anathema.library.model.BooleanValueModel;
import net.sf.anathema.library.number.IntegerRange;

public class ArmourStatsticsModel extends EquipmentStatisticsModel implements IArmourStatisticsModel {

  private final IIntValueModel bashingHardness = new RangedIntValueModel(new IntegerRange(0, Integer.MAX_VALUE), 0);
  private final IIntValueModel bashingSoak = new RangedIntValueModel(new IntegerRange(0, Integer.MAX_VALUE), 0);
  private final IIntValueModel fatigue = new RangedIntValueModel(new IntegerRange(0, Integer.MAX_VALUE), 0);
  private final IIntValueModel lethalHardness = new RangedIntValueModel(new IntegerRange(0, Integer.MAX_VALUE), 0);
  private final IIntValueModel lethalSoak = new RangedIntValueModel(new IntegerRange(0, Integer.MAX_VALUE), 0);
  private final IIntValueModel aggravatedSoak = new RangedIntValueModel(new IntegerRange(0, Integer.MAX_VALUE), 0);
  private final IIntValueModel mobilityPenalty = new RangedIntValueModel(new IntegerRange(Integer.MIN_VALUE, 0), 0);
  private final BooleanValueModel soakLinkModel = new BooleanValueModel(true);

  @Override
  public IIntValueModel getBashingHardnessModel() {
    return bashingHardness;
  }

  @Override
  public IIntValueModel getBashingSoakModel() {
    return bashingSoak;
  }

  @Override
  public IIntValueModel getFatigueModel() {
    return fatigue;
  }

  @Override
  public IIntValueModel getLethalHardnessModel() {
    return lethalHardness;
  }

  @Override
  public IIntValueModel getLethalSoakModel() {
    return lethalSoak;
  }

  @Override
  public IIntValueModel getMobilityPenaltyModel() {
    return mobilityPenalty;
  }

  @Override
  public IIntValueModel getSoakModel(HealthType healthType) {
    final IIntValueModel[] model = new IIntValueModel[1];
    healthType.accept(new IHealthTypeVisitor() {
      @Override
      public void visitAggravated(HealthType aggrevated) {
        model[0] = getAggravatedSoakModel();
      }

      @Override
      public void visitBashing(HealthType bashing) {
        model[0] = getBashingSoakModel();
      }

      @Override
      public void visitLethal(HealthType lethal) {
        model[0] = getLethalSoakModel();
      }
    });
    return model[0];
  }

  @Override
  public IIntValueModel getAggravatedSoakModel() {
    return aggravatedSoak;
  }

  @Override
  public BooleanValueModel getSoakLinkModel() {
    return soakLinkModel;
  }
}