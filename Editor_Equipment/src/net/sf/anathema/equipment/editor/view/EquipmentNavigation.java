package net.sf.anathema.equipment.editor.view;

import net.sf.anathema.library.interaction.model.Tool;
import net.sf.anathema.library.view.VetoableObjectSelectionView;
import net.sf.anathema.platform.repositorytree.VetorFactory;

public interface EquipmentNavigation extends VetorFactory {
  VetoableObjectSelectionView<String> getTemplateListView();

  Tool addEditTemplateTool();
}