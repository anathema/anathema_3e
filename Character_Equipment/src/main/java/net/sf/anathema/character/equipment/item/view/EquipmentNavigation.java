package net.sf.anathema.character.equipment.item.view;

import net.sf.anathema.framework.repository.tree.VetorFactory;
import net.sf.anathema.library.interaction.model.Tool;
import net.sf.anathema.library.view.VetoableObjectSelectionView;

public interface EquipmentNavigation extends VetorFactory {
  VetoableObjectSelectionView<String> getTemplateListView();

  Tool addEditTemplateTool();
}