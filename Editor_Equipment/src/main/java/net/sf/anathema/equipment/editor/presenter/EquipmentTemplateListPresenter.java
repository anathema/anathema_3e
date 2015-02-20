package net.sf.anathema.equipment.editor.presenter;

import java.util.Arrays;

import net.sf.anathema.equipment.editor.model.IEquipmentDatabaseManagement;
import net.sf.anathema.equipment.editor.model.IEquipmentTemplateEditModel;
import net.sf.anathema.equipment.editor.stats.model.StatsEditModel;
import net.sf.anathema.equipment.editor.view.EquipmentNavigation;
import net.sf.anathema.equipment.presentation.EquipmentTemplateNameComparator;
import net.sf.anathema.library.event.ObjectChangedListener;
import net.sf.anathema.library.resources.Resources;

public class EquipmentTemplateListPresenter {

  private final class EquipmentTemplateLoadListener implements ObjectChangedListener<String> {
    @Override
    public void valueChanged(String newValue) {
      if (newValue == null) {
        return;
      }
      IEquipmentTemplateEditModel editModel = model.getTemplateEditModel();
      editModel.setEditTemplate(newValue);
      EquipmentTemplateListPresenter.this.editModel.clearStatsSelection();
    }
  }

  private final Resources resources;
  private final EquipmentNavigation view;
  private final StatsEditModel editModel;
  private final IEquipmentDatabaseManagement model;

  public EquipmentTemplateListPresenter(
          Resources resources,
          IEquipmentDatabaseManagement model,
          EquipmentNavigation view, StatsEditModel editModel) {
    this.resources = resources;
    this.model = model;
    this.view = view;
    this.editModel = editModel;
  }

  public void initPresentation() {
    model.getDatabase().addAvailableTemplateChangeListener(this::updateAvailableTemplates);
    updateAvailableTemplates();
    view.getTemplateListView().addSelectionVetor(new DiscardChangesVetor(model, view, resources));
    view.getTemplateListView().addObjectSelectionChangedListener(new EquipmentTemplateLoadListener());
  }

  private void updateAvailableTemplates() {
    String[] templates = model.getDatabase().getAllAvailableTemplateIds();
    Arrays.sort(templates, new EquipmentTemplateNameComparator());
    view.getTemplateListView().setObjects(templates);
  }
}
