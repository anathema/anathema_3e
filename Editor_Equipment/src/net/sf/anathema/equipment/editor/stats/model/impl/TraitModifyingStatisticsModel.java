package net.sf.anathema.equipment.editor.stats.model.impl;

import net.sf.anathema.equipment.editor.stats.model.IIntValueModel;
import net.sf.anathema.equipment.editor.stats.model.ITraitModifyingStatisticsModel;
import net.sf.anathema.library.number.Range;

public class TraitModifyingStatisticsModel extends EquipmentStatisticsModel implements ITraitModifyingStatisticsModel {
  private final IIntValueModel DDVModel = new RangedIntValueModel(Range.unbounded(), 0);
  private final IIntValueModel PDVModel = new RangedIntValueModel(Range.unbounded(), 0);
  private final IIntValueModel MeleeAccuracyModel = new RangedIntValueModel(Range.unbounded(), 0);
  private final IIntValueModel MeleeDamageModel = new RangedIntValueModel(Range.unbounded(), 0);
  private final IIntValueModel RangedAccuracyModel = new RangedIntValueModel(Range.unbounded(), 0);
  private final IIntValueModel RangedDamageModel = new RangedIntValueModel(Range.unbounded(), 0);
  private final IIntValueModel JoinBattleModel = new RangedIntValueModel(Range.unbounded(), 0);

  @Override
  public IIntValueModel getDDVModel() {
    return DDVModel;
  }

  @Override
  public IIntValueModel getJoinBattleModel() {
    return JoinBattleModel;
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
}
