package net.sf.anathema.equipment.editor.presenter;

import net.sf.anathema.equipment.editor.model.IEquipmentDatabaseManagement;
import net.sf.anathema.equipment.editor.presenter.tools.CopyEquipmentTemplateTool;
import net.sf.anathema.equipment.editor.presenter.tools.NewEquipmentTemplateTool;
import net.sf.anathema.equipment.editor.presenter.tools.RemoveEquipmentTemplateTool;
import net.sf.anathema.equipment.editor.presenter.tools.SaveEquipmentTemplateTool;
import net.sf.anathema.equipment.editor.stats.model.StatsEditModel;
import net.sf.anathema.equipment.editor.stats.model.WrappingStatsEditModel;
import net.sf.anathema.equipment.editor.view.CostSelectionView;
import net.sf.anathema.equipment.editor.view.EquipmentDatabaseView;
import net.sf.anathema.equipment.editor.view.EquipmentDescriptionPanel;
import net.sf.anathema.library.resources.Resources;
import net.sf.anathema.library.text.ITextView;
import net.sf.anathema.library.text.TextualPresentation;

import com.google.common.collect.Lists;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class EquipmentDatabasePresenter {
  private final Resources resources;
  private final EquipmentDatabaseView view;
  private final IEquipmentDatabaseManagement model;
  private final List<String> defaultCostBackgrounds = Lists.newArrayList("Artifact", "Manse", "Resources");
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
    new NewEquipmentTemplateTool(resources, model, editModel).addToolTo(view);
    new SaveEquipmentTemplateTool(resources, model).addToolTo(view);
    new CopyEquipmentTemplateTool(resources, model, editModel).addToolTo(view);
    new RemoveEquipmentTemplateTool(resources, model, editModel).addToolTo(view);
  }

  private void initBasicDetailsView() {
    EquipmentDescriptionPanel descriptionPanel = view.addDescriptionPanel(resources.getString("Equipment.Creation.Basics"));
    ITextView nameView = descriptionPanel.addNameView(getColonString("Equipment.Creation.Basics.Name"));
    new TextualPresentation().initView(nameView, model.getTemplateEditModel().getDescription().getName());
    ITextView descriptionView = descriptionPanel.addDescriptionView(getColonString("Equipment.Creation.Basics.Description"));
    new TextualPresentation().initView(descriptionView, model.getTemplateEditModel().getDescription().getContent());
    Collection<String> backgrounds = getBackgroundStrings();
    final CostSelectionView costView = descriptionPanel.addCostView(getColonString("Equipment.Creation.Basics.Cost"));
    costView.setSelectableBackgrounds(backgrounds);
    model.getTemplateEditModel().addCostChangeListener(() -> costView.setValue(model.getTemplateEditModel().getCost()));
  }

  private Collection<String> getBackgroundStrings() {
    Stream<String> backgrounds = defaultCostBackgrounds.stream();
    return backgrounds.map(background -> resources.getString("Equipment.Cost.Type." + background)).collect(toList());
  }
}