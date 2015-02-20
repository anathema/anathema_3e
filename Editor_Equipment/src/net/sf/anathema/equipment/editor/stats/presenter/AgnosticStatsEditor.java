package net.sf.anathema.equipment.editor.stats.presenter;

import static net.sf.anathema.equipment.stats.EquipmentStatisticsType.Armor;
import static net.sf.anathema.equipment.stats.EquipmentStatisticsType.Artifact;
import static net.sf.anathema.equipment.stats.EquipmentStatisticsType.TraitModifying;
import static net.sf.anathema.equipment.stats.EquipmentStatisticsType.Weapon;
import net.sf.anathema.character.equipment.display.OperationResult;
import net.sf.anathema.character.equipment.display.userdialog.OperationResultHandler;
import net.sf.anathema.equipment.database.NullClosure;
import net.sf.anathema.equipment.editor.model.ModelToStats;
import net.sf.anathema.equipment.editor.presenter.EquipmentStatsDialog;
import net.sf.anathema.equipment.editor.presenter.EquipmentStatsView;
import net.sf.anathema.equipment.editor.presenter.TagPresenter;
import net.sf.anathema.equipment.editor.stats.model.IArtifactStatisticsModel;
import net.sf.anathema.equipment.editor.stats.model.IEquipmentStatisticsCreationModel;
import net.sf.anathema.equipment.editor.stats.model.IEquipmentStatisticsModel;
import net.sf.anathema.equipment.editor.stats.model.ITraitModifyingStatisticsModel;
import net.sf.anathema.equipment.editor.stats.model.IWeaponTagsModel;
import net.sf.anathema.equipment.editor.stats.model.TagsModel;
import net.sf.anathema.equipment.editor.stats.properties.ArmourTagProperties;
import net.sf.anathema.equipment.editor.stats.properties.WeaponTagProperties;
import net.sf.anathema.equipment.editor.stats.view.StatsEditor;
import net.sf.anathema.equipment.stats.IEquipmentStats;
import net.sf.anathema.equipment.stats.impl.ArmourTag;
import net.sf.anathema.library.collection.Closure;
import net.sf.anathema.library.resources.Resources;

public class AgnosticStatsEditor implements StatsEditor {

  private Closure<IEquipmentStats> whenChangesAreFinished = new NullClosure<>();
  private final ModelToStats modelToStats = new ModelToStats();

  @Override
  public void whenChangesAreConfirmed(Closure<IEquipmentStats> action) {
    this.whenChangesAreFinished = action;
  }

  @Override
  public void editStats(Resources resources, IEquipmentStatisticsCreationModel model, EquipmentStatsDialog dialog) {
    initPresentation(resources, model, dialog);
    dialog.show(new CreateStatsHandler(model));
  }

  private void initPresentation(Resources resources, IEquipmentStatisticsCreationModel model, EquipmentStatsDialog dialog) {
    if (Weapon == model.getEquipmentType()) {
      initWeaponPresentation(resources, model, dialog);
    } else if (Armor == model.getEquipmentType()) {
      initArmourPresentation(resources, model, dialog);
    } else if (Artifact == model.getEquipmentType()) {
      initArtifactPresentation(resources, model, dialog);
    } else if (TraitModifying == model.getEquipmentType()) {
      initTraitModifyingPresentation(resources, model, dialog);
    }
  }

  private void initWeaponPresentation(Resources resources, IEquipmentStatisticsCreationModel model, EquipmentStatsDialog dialog) {
    EquipmentStatsView view = dialog.getEquipmentStatsView();
    IEquipmentStatisticsModel nameModel = model.getWeaponModel();
    IWeaponTagsModel tagModel = model.getWeaponTagsModel();
    new GeneralStatsPresenter(view, dialog, nameModel, model, resources).initPresentation();
    new TagPresenter<>(tagModel, view, new WeaponTagProperties(resources)).initPresentation();
  }

  private void initArmourPresentation(Resources resources, IEquipmentStatisticsCreationModel model, EquipmentStatsDialog dialog) {
    EquipmentStatsView view = dialog.getEquipmentStatsView();
    IEquipmentStatisticsModel armourModel = model.getArmorModel();
    TagsModel<ArmourTag> tagModel = model.getArmorTagsModel();
    new GeneralStatsPresenter(view, dialog, armourModel, model, resources).initPresentation();
    new TagPresenter<>(tagModel, view, new ArmourTagProperties(resources)).initPresentation();
  }

  private void initArtifactPresentation(Resources resources, IEquipmentStatisticsCreationModel model,
                                        EquipmentStatsDialog dialog) {
    EquipmentStatsView view = dialog.getEquipmentStatsView();
    IArtifactStatisticsModel artifactModel = model.getArtifactStatisticsModel();
    new GeneralStatsPresenter(view, dialog, artifactModel, model, resources).initPresentation();
    new ArtifactStatisticsPresenter(artifactModel, view, resources).initPresentation();
  }

  private void initTraitModifyingPresentation(Resources resources, IEquipmentStatisticsCreationModel model,
                                              EquipmentStatsDialog dialog) {
    EquipmentStatsView view = dialog.getEquipmentStatsView();
    ITraitModifyingStatisticsModel modModel = model.getTraitModifyingStatisticsModel();
    new GeneralStatsPresenter(view, dialog, modModel, model, resources).initPresentation();
    new ModifierStatisticsPresenter(modModel, view, resources).initPresentation();
  }

  private class CreateStatsHandler implements OperationResultHandler {
    private final IEquipmentStatisticsCreationModel model;

    public CreateStatsHandler(IEquipmentStatisticsCreationModel model) {
      this.model = model;
    }

    @Override
    public void handleOperationResult(OperationResult result) {
      if (result.isCanceled()) {
        return;
      }
      IEquipmentStats stats = modelToStats.createStats(model);
      whenChangesAreFinished.execute(stats);
    }
  }
}