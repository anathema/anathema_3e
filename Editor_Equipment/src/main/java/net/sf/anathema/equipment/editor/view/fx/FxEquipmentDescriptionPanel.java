package net.sf.anathema.equipment.editor.view.fx;

import javafx.scene.Node;

import net.miginfocom.layout.AC;
import net.miginfocom.layout.CC;
import net.miginfocom.layout.LC;
import net.sf.anathema.equipment.editor.view.CostSelectionView;
import net.sf.anathema.equipment.editor.view.EquipmentDescriptionPanel;
import net.sf.anathema.library.fx.selection.SelectionViewFactory;
import net.sf.anathema.library.fx.text.FxTextView;
import net.sf.anathema.library.text.ITextView;

import org.tbee.javafx.scene.layout.MigPane;

public class FxEquipmentDescriptionPanel implements EquipmentDescriptionPanel {

  private final SelectionViewFactory selectionViewFactory;
  private MigPane pane;

  public FxEquipmentDescriptionPanel(SelectionViewFactory selectionFactory) {
    this.selectionViewFactory = selectionFactory;
    pane = new MigPane(new LC().wrapAfter(1).fill().insets("4"), new AC(), new AC().index(1).shrinkPrio(200));
  }

  @Override
  public ITextView addNameView(String label) {
    final FxTextView view = FxTextView.SingleLine(label);
    pane.add(view.getNode(), new CC().growX().pushY().span());
    return view;
  }

  @Override
  public ITextView addDescriptionView(String label) {
    final FxTextView view = FxTextView.MultiLine(label);
    pane.add(view.getNode(), new CC().growX().pushY().span());
    return view;
  }

  @Override
  public CostSelectionView addCostView(String label) {
    final FxCostSelectionView costSelectionView = new FxCostSelectionView(label, selectionViewFactory);
    pane.add(costSelectionView.getNode(), new CC().split(2).pushX());
    return costSelectionView;
  }

  public Node getNode() {
    return pane;
  }
}