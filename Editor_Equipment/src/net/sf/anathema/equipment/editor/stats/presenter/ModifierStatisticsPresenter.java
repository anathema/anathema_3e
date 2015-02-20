package net.sf.anathema.equipment.editor.stats.presenter;

import net.sf.anathema.equipment.editor.presenter.EquipmentStatsView;
import net.sf.anathema.equipment.editor.stats.model.IIntValueModel;
import net.sf.anathema.equipment.editor.stats.model.ITraitModifyingStatisticsModel;
import net.sf.anathema.equipment.editor.stats.properties.TraitBoostStatisticsProperties;
import net.sf.anathema.library.fx.configurableview.IIntegerSpinner;
import net.sf.anathema.library.resources.Resources;

public class ModifierStatisticsPresenter {
  private final ITraitModifyingStatisticsModel modModel;
  private final EquipmentStatsView view;
  private final TraitBoostStatisticsProperties properties;

  public ModifierStatisticsPresenter(ITraitModifyingStatisticsModel modModel, EquipmentStatsView view,
                                     Resources resources) {
    this.modModel = modModel;
    this.view = view;
    this.properties = new TraitBoostStatisticsProperties(resources);
  }

  public void initPresentation() {
    addSpinner(properties.getDDVLabel(), modModel.getDDVModel());
    addSpinner(properties.getPDVLabel(), modModel.getPDVModel());

    addSpinner(properties.getMeleeAccuracyLabel(), modModel.getMeleeWeaponAccuracyModel());
    addSpinner(properties.getMeleeDamageLabel(), modModel.getMeleeWeaponDamageModel());

    addSpinner(properties.getRangedAccuracyLabel(), modModel.getRangedWeaponAccuracyModel());
    addSpinner(properties.getRangedDamageLabel(), modModel.getRangedWeaponDamageModel());

    addSpinner(properties.getJoinBattleLabel(), modModel.getJoinBattleModel());
  }

  private void addSpinner(String label, IIntValueModel model) {
    IIntegerSpinner spinner = view.addIntegerSpinner(label);
    new IntValuePresentation().initPresentation(spinner, model);
  }
}
