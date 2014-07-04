package net.sf.anathema.character.equipment.creation.model;

import net.sf.anathema.character.equipment.creation.presenter.IIntValueModel;
import net.sf.anathema.character.equipment.creation.presenter.ITraitModifyingStatisticsModel;
import net.sf.anathema.character.equipment.creation.presenter.RangedIntValueModel;
import net.sf.anathema.library.number.IntegerRange;

public class TraitModifyingStatisticsModel extends EquipmentStatisticsModel implements ITraitModifyingStatisticsModel {
  private final IIntValueModel DDVModel = new RangedIntValueModel(new IntegerRange(Integer.MIN_VALUE, Integer.MAX_VALUE), 0);
  private final IIntValueModel PDVModel = new RangedIntValueModel(new IntegerRange(Integer.MIN_VALUE, Integer.MAX_VALUE), 0);
  private final IIntValueModel MDDVModel = new RangedIntValueModel(new IntegerRange(Integer.MIN_VALUE, Integer.MAX_VALUE), 0);
  private final IIntValueModel MPDVModel = new RangedIntValueModel(new IntegerRange(Integer.MIN_VALUE, Integer.MAX_VALUE), 0);
  private final IIntValueModel MeleeSpeedModel = new RangedIntValueModel(new IntegerRange(Integer.MIN_VALUE, Integer.MAX_VALUE), 0);
  private final IIntValueModel MeleeAccuracyModel = new RangedIntValueModel(new IntegerRange(Integer.MIN_VALUE, Integer.MAX_VALUE), 0);
  private final IIntValueModel MeleeDamageModel = new RangedIntValueModel(new IntegerRange(Integer.MIN_VALUE, Integer.MAX_VALUE), 0);
  private final IIntValueModel MeleeRateModel = new RangedIntValueModel(new IntegerRange(Integer.MIN_VALUE, Integer.MAX_VALUE), 0);
  private final IIntValueModel RangedSpeedModel = new RangedIntValueModel(new IntegerRange(Integer.MIN_VALUE, 0), 0);
  private final IIntValueModel RangedAccuracyModel = new RangedIntValueModel(new IntegerRange(Integer.MIN_VALUE, Integer.MAX_VALUE), 0);
  private final IIntValueModel RangedDamageModel = new RangedIntValueModel(new IntegerRange(Integer.MIN_VALUE, Integer.MAX_VALUE), 0);
  private final IIntValueModel RangedRateModel = new RangedIntValueModel(new IntegerRange(Integer.MIN_VALUE, Integer.MAX_VALUE), 0);
  private final IIntValueModel JoinBattleModel = new RangedIntValueModel(new IntegerRange(Integer.MIN_VALUE, Integer.MAX_VALUE), 0);
  private final IIntValueModel JoinDebateModel = new RangedIntValueModel(new IntegerRange(Integer.MIN_VALUE, Integer.MAX_VALUE), 0);
  private final IIntValueModel JoinWarModel = new RangedIntValueModel(new IntegerRange(Integer.MIN_VALUE, Integer.MAX_VALUE), 0);

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
