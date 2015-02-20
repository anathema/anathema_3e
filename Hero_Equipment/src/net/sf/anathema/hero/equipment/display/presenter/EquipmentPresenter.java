package net.sf.anathema.hero.equipment.display.presenter;

import static net.sf.anathema.library.lang.StringUtilities.isNullOrTrimmedEmpty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.sf.anathema.equipment.character.IEquipmentItem;
import net.sf.anathema.equipment.core.EquipmentStringBuilder;
import net.sf.anathema.equipment.core.EquipmentTemplateNameComparator;
import net.sf.anathema.hero.equipment.EquipmentHeroEvaluator;
import net.sf.anathema.hero.equipment.EquipmentModel;
import net.sf.anathema.hero.equipment.EquipmentObjectPresenter;
import net.sf.anathema.hero.equipment.EquipmentObjectView;
import net.sf.anathema.hero.equipment.EquipmentOptionsProvider;
import net.sf.anathema.library.event.CollectionListener;
import net.sf.anathema.library.interaction.model.Tool;
import net.sf.anathema.library.resources.Resources;
import net.sf.anathema.library.view.ObjectSelectionView;
import net.sf.anathema.library.view.VetoableObjectSelectionView;
import net.sf.anathema.platform.taskbar.BasicUi;

public class EquipmentPresenter {

  private final Resources resources;
  private final EquipmentModel model;
  private final EquipmentView view;
  private ObjectSelectionView<IEquipmentItem> ownedEquipmentOverview;
  private EquipmentObjectPresenter objectPresenter;

  public EquipmentPresenter(Resources resources, EquipmentModel model, EquipmentView view) {
    this.resources = resources;
    this.model = model;
    this.view = view;
    EquipmentHeroEvaluator heroEvaluator = model.getHeroEvaluator();
    EquipmentOptionsProvider optionProvider = model.getOptionProvider();
    this.objectPresenter = new EquipmentObjectPresenter(new EquipmentStringBuilder(resources), heroEvaluator, optionProvider, resources);
  }

  public void initPresentation() {
    VetoableObjectSelectionView<String> equipmentTemplatePickList = view.getEquipmentTemplatePickList();
    this.ownedEquipmentOverview = view.addOwnedEquipmentList(new FilteringEquipmentItemRenderer(resources, model.getHeroEvaluator()));
    model.addEquipmentObjectListener(new UpdateOwnedItems());
    equipmentTemplatePickList.setCellRenderer(new EquipmentItemUIConfiguration(model, resources));
    setObjects(equipmentTemplatePickList);
    addAddButton(equipmentTemplatePickList);
    addRemoveButton();
    addRefreshTool(equipmentTemplatePickList);
    EquipmentObjectView editView = view.addItemEditView();
    ownedEquipmentOverview.addObjectSelectionChangedListener(item -> initItemPresentation(item, editView));
    refreshOwnedItemOverview();
  }

  private void addRemoveButton() {
    Tool remove = view.addToolButton();
    remove.setIcon(new BasicUi().getLeftArrowIconPath());
    remove.setTooltip(resources.getString("AdditionalTemplateView.RemoveTemplate.Action.Name"));
    remove.setCommand(() -> model.removeItem(ownedEquipmentOverview.getSelectedObject()));
    ownedEquipmentOverview.addObjectSelectionChangedListener(newValue -> {
      if (newValue == null || !(model.canBeRemoved(newValue))) {
        remove.disable();
      } else {
        remove.enable();
      }
    });
    remove.disable();
  }

  private void addAddButton(VetoableObjectSelectionView<String> equipmentTemplatePickList) {
    Tool addTool = view.addToolButton();
    addTool.setIcon(new BasicUi().getRightArrowIconPath());
    addTool.setTooltip(resources.getString("AdditionalTemplateView.AddTemplate.Action.Tooltip"));
    addTool.setCommand(() -> model.addItem(equipmentTemplatePickList.getSelectedObject()));
    equipmentTemplatePickList.addObjectSelectionChangedListener(newValue -> setEnabled(newValue, addTool));
    setEnabled(equipmentTemplatePickList.getSelectedObject(), addTool);
  }

  private void addRefreshTool(VetoableObjectSelectionView<String> equipmentTemplatePickList) {
    Tool refreshTool = view.addToolButton();
    refreshTool.setTooltip(resources.getString("AdditionalTemplateView.RefreshDatabase.Action.Tooltip"));
    refreshTool.setIcon(new EquipmentUI().getRefreshIconPath());
    refreshTool.setCommand(() -> {
      setObjects(equipmentTemplatePickList);
      model.refreshItems();
    });
  }

  private void setObjects(VetoableObjectSelectionView<String> equipmentTemplatePickList) {
    String[] templates = model.getAvailableTemplateIds();
    Arrays.sort(templates, new EquipmentTemplateNameComparator());
    equipmentTemplatePickList.setObjects(templates);
  }

  private void setEnabled(String newValue, Tool selectTool) {
    if (isNullOrTrimmedEmpty(newValue)) {
      selectTool.disable();
    } else {
      selectTool.enable();
    }
  }

  private void initItemPresentation(IEquipmentItem item, EquipmentObjectView objectView) {
    if (item == null) {
      objectView.clear();
      return;
    }
    objectPresenter.initPresentation(item, objectView);
    if (model.canBeRemoved(item)) {
      objectPresenter.initPersonalization();
    }
  }

  private void refreshOwnedItemOverview() {
    List<IEquipmentItem> allItems = new ArrayList<>();
    allItems.addAll(model.getNaturalWeapons());
    allItems.addAll(model.getEquipmentItems());
    ownedEquipmentOverview.setObjects(allItems);
  }

  private class UpdateOwnedItems implements CollectionListener {
    @Override
    public void itemAdded() {
      refreshOwnedItemOverview();
    }

    @Override
    public void itemRemoved() {
      refreshOwnedItemOverview();
    }
  }
}