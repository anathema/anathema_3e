package net.sf.anathema.character.equipment.item;

import net.sf.anathema.character.equipment.item.model.IEquipmentDatabaseManagement;
import net.sf.anathema.character.equipment.item.view.CostSelectionView;
import net.sf.anathema.character.equipment.item.view.EquipmentDatabaseView;
import net.sf.anathema.character.equipment.item.view.EquipmentDescriptionPanel;
import net.sf.anathema.equipment.core.ItemCost;
import net.sf.anathema.equipment.core.MagicalMaterial;
import net.sf.anathema.equipment.core.MaterialComposition;
import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.lib.gui.selection.ObjectSelectionView;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;
import net.sf.anathema.lib.workflow.textualdescription.TextualPresentation;

import static net.sf.anathema.lib.lang.ArrayUtilities.transform;

public class EquipmentDatabasePresenter {
  private final Resources resources;
  private final EquipmentDatabaseView view;
  private final IEquipmentDatabaseManagement model;
  private final String[] defaultCostBackgrounds = {"Artifact", "Manse", "Resources"};
  private final StatsEditModel editModel;

  public EquipmentDatabasePresenter(Resources resources, IEquipmentDatabaseManagement model,
                                    EquipmentDatabaseView view) {
    this.resources = resources;
    this.model = model;
    this.view = view;
    this.editModel = new WrappingStatsEditModel(model);
  }

  public void initPresentation() {
    new EquipmentTemplateListPresenter(resources, model, view,editModel).initPresentation();
    addEditTemplateActions();
    initBasicDetailsView();
    new EquipmentEditStatsPresenter(resources, editModel, view).initPresentation();
    model.getTemplateEditModel().setNewTemplate();
  }

  private String getColonString(String key) {
    return resources.getString(key) + ":";
  }

  private void addEditTemplateActions() {
    new NewEquipmentTemplateAction(resources, model, editModel).addToolTo(view);
    new SaveEquipmentTemplateAction(resources, model).addToolTo(view);
    new CopyEquipmentTemplateAction(resources, model, editModel).addToolTo(view);
    new RemoveEquipmentTemplateAction(resources, model, editModel).addToolTo(view);
  }

  private void initBasicDetailsView() {
    EquipmentDescriptionPanel descriptionPanel = view.addDescriptionPanel(resources.getString("Equipment.Creation.Basics"));
    ITextView nameView = descriptionPanel.addNameView(getColonString("Equipment.Creation.Basics.Name"));
    new TextualPresentation().initView(nameView, model.getTemplateEditModel().getDescription().getName());
    ITextView descriptionView = descriptionPanel.addDescriptionView(getColonString("Equipment.Creation.Basics.Description"));
    new TextualPresentation().initView(descriptionView, model.getTemplateEditModel().getDescription().getContent());
    final ObjectSelectionView<MaterialComposition> compositionView = descriptionPanel.addCompositionView(getColonString("Equipment.Creation.Basics.Composition"), new CompositionUi(resources));
    compositionView.setObjects(MaterialComposition.values());
    final ObjectSelectionView<MagicalMaterial> materialView = descriptionPanel.addMaterialView(getColonString("Equipment.Creation.Basics.Material"), new MaterialUi(resources));
    materialView.setObjects(MagicalMaterial.values());
    String[] backgrounds = transform(defaultCostBackgrounds, String.class, background -> resources.getString("Equipment.Cost.Type." + background));
    final CostSelectionView costView = descriptionPanel.addCostView(getColonString("Equipment.Creation.Basics.Cost"));
    costView.setSelectableBackgrounds(backgrounds);
    compositionView.addObjectSelectionChangedListener(newValue -> model.getTemplateEditModel().setMaterialComposition(newValue));
    model.getTemplateEditModel().addCompositionChangeListener(() -> {
      MaterialComposition materialComposition = model.getTemplateEditModel().getMaterialComposition();
      compositionView.setSelectedObject(materialComposition);
      materialView.setEnabled(materialComposition.requiresMaterial());
    });
    materialView.addObjectSelectionChangedListener(newValue -> model.getTemplateEditModel().setMagicalMaterial(newValue));
    model.getTemplateEditModel().addMagicalMaterialChangeListener(() -> materialView.setSelectedObject(model.getTemplateEditModel().getMagicalMaterial()));
    costView.addSelectionChangedListener((selection, value) -> {
      ItemCost cost = selection == null ? null : new ItemCost(selection, value);
      ItemCost currentModelCost = model.getTemplateEditModel().getCost();
      if ((cost == null && currentModelCost != null) ||
              (cost != null && currentModelCost == null) ||
              (cost != null && !cost.equals(currentModelCost))) {
        model.getTemplateEditModel().setCost(cost);
      }
    });
    model.getTemplateEditModel().addCostChangeListener(() -> costView.setValue(model.getTemplateEditModel().getCost()));
  }
}