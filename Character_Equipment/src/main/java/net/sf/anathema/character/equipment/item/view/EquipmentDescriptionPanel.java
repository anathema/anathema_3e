package net.sf.anathema.character.equipment.item.view;

import net.sf.anathema.equipment.core.MagicalMaterial;
import net.sf.anathema.equipment.core.MaterialComposition;
import net.sf.anathema.library.presenter.AgnosticUIConfiguration;
import net.sf.anathema.library.text.ITextView;
import net.sf.anathema.library.view.ObjectSelectionView;

public interface EquipmentDescriptionPanel {

  ITextView addNameView(String label);

  ITextView addDescriptionView(String label);

  ObjectSelectionView<MaterialComposition> addCompositionView(String label, AgnosticUIConfiguration<MaterialComposition> ui);

  ObjectSelectionView<MagicalMaterial> addMaterialView(String label, AgnosticUIConfiguration<MagicalMaterial> ui);

  CostSelectionView addCostView(String label);
}