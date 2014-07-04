package net.sf.anathema.character.equipment.creation.model;

import net.sf.anathema.character.equipment.creation.presenter.IIntValueModel;
import net.sf.anathema.character.equipment.creation.presenter.ITraitModifyingStatisticsModel;
import net.sf.anathema.character.equipment.creation.presenter.RangedIntValueModel;
import net.sf.anathema.library.number.Range;

public class TraitModifyingStatisticsModel extends EquipmentStatisticsModel implements ITraitModifyingStatisticsModel {
  private final IIntValueModel DDVModel = new RangedIntValueModel(Range.unbounded(), 0);
  private final IIntValueModel PDVModel = new RangedIntValueModel(Range.unbounded(), 0);
  private final IIntValueModel MDDVModel = new RangedIntValueModel(Range.unbounded(), 0);
  private final IIntValueModel MPDVModel = new RangedIntValueModel(Range.unbounded(), 0);
  private final IIntValueModel MeleeSpeedModel = new RangedIntValueModel(Range.unbounded(), 0);
  private final IIntValueModel MeleeAccuracyModel = new RangedIntValueModel(Range.unbounded(), 0);
  private final IIntValueModel MeleeDamageModel = new RangedIntValueModel(Range.unbounded(), 0);
  private final IIntValueModel MeleeRateModel = new RangedIntValueModel(Range.unbounded(), 0);
  private final IIntValueModel RangedSpeedModel = new RangedIntValueModel(Range.boundedFromAbove(0), 0);
  private final IIntValueModel RangedAccuracyModel = new RangedIntValueModel(Range.unbounded(), 0);
  private final IIntValueModel RangedDamageModel = new RangedIntValueModel(Range.unbounded(), 0);
  private final IIntValueModel RangedRateModel = new RangedIntValueModel(Range.unbounded(), 0);
  private final IIntValueModel JoinBattleModel = new RangedIntValueModel(Range.unbounded(), 0);
  private final IIntValueModel JoinDebateModel = new RangedIntValueModel(Range.unbounded(), 0);
  private final IIntValueModel JoinWarModel = new RangedIntValueModel(Range.unbounded(), 0);

  @Override
  public IIntValueModel getDDVModel() {
    return DDVModel;
  }

  @Override
  public IIntValueModel getJoinBattleModel() {
    return JoinBattleModel;
  }

  @Override
  public IIntValueModel getJoinDebateModel() {
    return JoinDebateModel;
  }

  @Override
  public IIntValueModel getJoinWarModel() {
    return JoinWarModel;
  }

  @Override
  public IIntValueModel getMDDVModel() {
    return MDDVModel;
  }

  @Override
  public IIntValueModel getMPDVModel() {
    return MPDVModel;
  }

  @Override
  public IIntValueModel getMeleeWeaponAccuracyModel() {
    return MeleeAccuracyModel;
  }

  @Override
  public IIntValueModel getMeleeWeaponDamageModel() {
    return MeleeDamageModel;
  }

  @Override
  public IIntValueModel getMeleeWeaponRateModel() {
    return MeleeRateModel;
  }

  @Override
  public IIntValueModel getMeleeWeaponSpeedModel() {
    return MeleeSpeedModel;
  }

  @Override
  public IIntValueModel getPDVModel() {
    return PDVModel;
  }

  @Override
  public IIntValueModel getRangedWeaponAccuracyModel() {
    return RangedAccuracyModel;
  }

  @Override
  public IIntValueModel getRangedWeaponDamageModel() {
    return RangedDamageModel;
  }

  @Override
  public IIntValueModel getRangedWeaponRateModel() {
    return RangedRateModel;
  }

  @Override
  public IIntValueModel getRangedWeaponSpeedModel() {
    return RangedSpeedModel;
  }

}
