package net.sf.anathema.equipment.editor.view;

import net.sf.anathema.equipment.editor.presenter.EquipmentStatsDialog;
import net.sf.anathema.equipment.stats.IEquipmentStats;
import net.sf.anathema.library.interaction.model.Tool;
import net.sf.anathema.library.presenter.AgnosticUIConfiguration;
import net.sf.anathema.library.view.VetoableObjectSelectionView;
import net.sf.anathema.library.view.Vetor;

public class AgnosticEquipmentDatabaseView implements EquipmentDatabaseView {

  private final EquipmentNavigation navigation;
  private final EquipmentDetails details;

  public AgnosticEquipmentDatabaseView(EquipmentNavigation navigation, EquipmentDetails details) {
    this.navigation = navigation;
    this.details = details;
  }

  @Override
  public ToolListView<IEquipmentStats> initStatsListView(
          String title, AgnosticUIConfiguration<IEquipmentStats> configuration) {
    return details.initStatsListView(title, configuration);
  }

  @Override
  public EquipmentDescriptionPanel addDescriptionPanel(String title) {
    return details.addDescriptionPanel(title);
  }

  @Override
  public VetoableObjectSelectionView<String> getTemplateListView() {
    return navigation.getTemplateListView();
  }

  @Override
  public Tool addEditTemplateTool() {
    return navigation.addEditTemplateTool();
  }

  @Override
  public Vetor createVetor(String title, String message) {
    return navigation.createVetor(title, message);
  }

  @Override
  public EquipmentStatsDialog createEquipmentStatsDialog() {
    return details.createEquipmentStatsDialog();
  }
}
