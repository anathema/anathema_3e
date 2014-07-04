package net.sf.anathema.character.equipment.creation.presenter;

import net.sf.anathema.character.equipment.creation.model.ArmourTag;
import net.sf.anathema.character.equipment.creation.presenter.stats.properties.ArmourTagProperties;
import net.sf.anathema.character.equipment.creation.presenter.stats.properties.WeaponTagProperties;
import net.sf.anathema.character.equipment.display.OperationResult;
import net.sf.anathema.character.equipment.display.userdialog.OperationResultHandler;
import net.sf.anathema.character.equipment.item.model.ModelToStats;
import net.sf.anathema.character.equipment.item.model.NullClosure;
import net.sf.anathema.character.equipment.item.model.StatsEditor;
import net.sf.anathema.hero.equipment.sheet.content.stats.weapon.IEquipmentStats;
import net.sf.anathema.lib.util.Closure;
import net.sf.anathema.library.resources.Resources;

import static net.sf.anathema.character.equipment.item.model.EquipmentStatisticsType.Armor;
import static net.sf.anathema.character.equipment.item.model.EquipmentStatisticsType.Artifact;
import static net.sf.anathema.character.equipment.item.model.EquipmentStatisticsType.TraitModifying;
import static net.sf.anathema.character.equipment.item.model.EquipmentStatisticsType.Weapon;

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
    IArmourStatisticsModel armourModel = model.getArmourStatisticsModel();
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