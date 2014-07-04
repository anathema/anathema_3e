package net.sf.anathema.hero.equipment.display.presenter;

import net.sf.anathema.character.equipment.character.model.IEquipmentItem;
import net.sf.anathema.equipment.core.MagicalMaterial;
import net.sf.anathema.library.interaction.model.Tool;
import net.sf.anathema.library.presenter.AgnosticUIConfiguration;
import net.sf.anathema.library.view.ObjectSelectionView;
import net.sf.anathema.library.view.VetoableObjectSelectionView;

public interface EquipmentView {

  VetoableObjectSelectionView<String> getEquipmentTemplatePickList();

  Tool addToolButton();

  MagicalMaterialView addMagicMaterialView(String label, AgnosticUIConfiguration<MagicalMaterial> renderer);

  ObjectSelectionView<IEquipmentItem> addOwnedEquipmentList(EquipmentItemRenderer renderer);

  EquipmentObjectView addItemEditView();
}