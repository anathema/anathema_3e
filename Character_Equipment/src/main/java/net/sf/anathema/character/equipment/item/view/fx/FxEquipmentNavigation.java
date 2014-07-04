package net.sf.anathema.character.equipment.item.view.fx;

import net.sf.anathema.character.equipment.item.view.EquipmentNavigation;
import net.sf.anathema.framework.repository.tree.FxVetor;
import net.sf.anathema.library.interaction.AcceleratorMap;
import net.sf.anathema.library.interaction.model.Tool;
import net.sf.anathema.library.view.VetoableObjectSelectionView;
import net.sf.anathema.library.view.Vetor;
import net.sf.anathema.platform.fx.ListSelectionView;
import net.sf.anathema.platform.fx.Navigation;

public class FxEquipmentNavigation extends Navigation implements EquipmentNavigation {

  private ListSelectionView<String> listView = new ListSelectionView<>();

  public FxEquipmentNavigation(AcceleratorMap acceleratorMap) {
    super(acceleratorMap);
    addContainerToNavigation(listView.getNode());
  }

  @Override
  public VetoableObjectSelectionView<String> getTemplateListView() {
    return listView;
  }

  @Override
  public Tool addEditTemplateTool() {
    return addTool();
  }

  @Override
  public Vetor createVetor(String title, String message) {
    return new FxVetor(title, message);
  }
}