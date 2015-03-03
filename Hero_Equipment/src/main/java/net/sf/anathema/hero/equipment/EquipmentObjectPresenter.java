package net.sf.anathema.hero.equipment;

import net.sf.anathema.equipment.character.IEquipmentItem;
import net.sf.anathema.equipment.presentation.IEquipmentObjectPresenter;
import net.sf.anathema.equipment.presentation.IEquipmentStringBuilder;
import net.sf.anathema.equipment.stats.IArtifactStats;
import net.sf.anathema.equipment.stats.IEquipmentStats;
import net.sf.anathema.equipment.stats.IWeaponStats;
import net.sf.anathema.hero.equipment.display.presenter.StatsPresentationFactory;
import net.sf.anathema.hero.equipment.display.presenter.StatsPresentationStrategy;
import net.sf.anathema.hero.equipment.display.presenter.StatsView;
import net.sf.anathema.hero.equipment.model.EquipmentItemPresentationModel;
import net.sf.anathema.hero.equipment.model.EquipmentSpecialtyOption;
import net.sf.anathema.hero.equipment.model.natural.NaturalWeaponTemplate;
import net.sf.anathema.hero.specialties.model.Specialty;
import net.sf.anathema.library.resources.Resources;

import java.text.MessageFormat;

public class EquipmentObjectPresenter implements IEquipmentObjectPresenter {

  
  private final EquipmentItemPresentationModel presentationModel = new EquipmentItemPresentationModel();
  private final IEquipmentStringBuilder stringBuilder;
  private final EquipmentOptionsProvider characterOptionProvider;
  private final EquipmentHeroEvaluator dataProvider;
  private final Resources resources;
  private EquipmentObjectView view;
  private IEquipmentItem model;

  public EquipmentObjectPresenter(IEquipmentStringBuilder stringBuilder,
                                  EquipmentHeroEvaluator dataProvider, EquipmentOptionsProvider characterOptionProvider,
                                  Resources resources) {
    this.stringBuilder = stringBuilder;
    this.characterOptionProvider = characterOptionProvider;
    this.resources = resources;
    this.dataProvider = dataProvider;
  }

  public void initPresentation(IEquipmentItem item, EquipmentObjectView objectView) {
    if (view != null) {
      view.disablePersonalization();
    }
    this.model = item;
    this.view = objectView;
    showItemTitle();
    showItemDescription();
    refreshView();
  }

  public void initPersonalization() {
    view.enablePersonalization();
    view.whenTitleChanges(model::setTitle);
    view.whenDescriptionChanges(model::setDescription);
  }

  private void showItemTitle() {
    String itemTitle = model.getTitle();
    if (resources.supportsKey(EQUIPMENT_NAME_PREFIX + itemTitle)) {
      itemTitle = resources.getString(EQUIPMENT_NAME_PREFIX + itemTitle);
    }
    view.setItemTitle(itemTitle);
  }

  private void showItemDescription() {
    String description = model.getDescription();
    if (resources.supportsKey(DESCRIPTION_PREFIX + description)) {
      description = resources.getString(DESCRIPTION_PREFIX + description);
    }
    if (description != null) {
      view.setItemDescription(description);
    }
  }

  private void refreshView() {
    view.clearStats();
    presentationModel.clear();
    for (IEquipmentStats stats : model.getStats()) {
      StatsPresentationStrategy strategy = new StatsPresentationFactory(dataProvider, model).choosePresentationStrategy(stats);
      if (!strategy.shouldStatsBeShown()) {
        continue;
      }
      StatsView statsView = view.addStats(createEquipmentDescription(model, stats));
      statsView.setSelected(model.isPrintEnabled(stats));
      statsView.addChangeListener(() -> {
        model.setPrintEnabled(stats, statsView.getSelected());
        if(model.getTemplateId().equals(NaturalWeaponTemplate.NATURAL))
        {
        	refreshView();
        }
      });
      if (stats instanceof IArtifactStats) {
        presentationModel.registerViewForAttunementStats(stats, statsView);
      } else {
        presentationModel.registerViewForDefaultStats(stats, statsView);
      }
      showEligibleSpecialties(stats, statsView);
    }
  }

  private void showEligibleSpecialties(IEquipmentStats equipment, StatsView statsView) {
    if (!(equipment instanceof IWeaponStats)) {
      return;
    }
    addSpecialtiesForWeaponStats(statsView, (IWeaponStats) equipment);
  }

  private void addSpecialtiesForWeaponStats(StatsView baseView, IWeaponStats weaponStats) {
    for (Specialty specialty : dataProvider.getSpecialties(weaponStats.getTraitType())) {
      String label = MessageFormat.format(resources.getString("Equipment.Specialty"), specialty.getDescription());
      StatsView statsView = baseView.addOptionFlag(label);
      IEquipmentStatsOption specialtyOption = new EquipmentSpecialtyOption(specialty, weaponStats.getTraitType());
      IEquipmentStats baseStat = model.getStat(weaponStats.getId());
      statsView.setSelected(characterOptionProvider.isStatOptionEnabled(model, baseStat, specialtyOption));
      statsView.addChangeListener(() -> {
        if (statsView.getSelected()) characterOptionProvider.enableStatOption(model, baseStat, specialtyOption);
        else characterOptionProvider.disableStatOption(model, baseStat, specialtyOption);
      });
    }
  }

  private String createEquipmentDescription(IEquipmentItem item, IEquipmentStats equipment) {
    return stringBuilder.createString(item, equipment);
  }
}