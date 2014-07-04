package net.sf.anathema.hero.display.fx.configurableview;

import javafx.scene.Node;
import net.miginfocom.layout.CC;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.gui.AgnosticUIConfiguration;
import net.sf.anathema.lib.gui.selection.ObjectSelectionView;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;
import net.sf.anathema.platform.fx.FxObjectSelectionView;
import net.sf.anathema.platform.fx.FxTextView;
import net.sf.anathema.platform.fx.selection.ComboBoxSelectionView;
import net.sf.anathema.platform.tool.FxButtonTool;
import org.tbee.javafx.scene.layout.MigPane;

import static net.sf.anathema.lib.gui.layout.LayoutUtils.withoutInsets;

public class FxConfigurableSingleLineView {

  private MigPane pane = new MigPane(withoutInsets());

  public ITextView addLineView(String labelText) {
    FxTextView view = FxTextView.SingleLine(labelText);
    pane.add(view.getNode(), new CC().growX());
    return view;
  }

  public Tool addEditAction() {
    FxButtonTool interaction = FxButtonTool.ForToolbar();
    pane.add(interaction.getNode(), new CC().growX().alignY("center"));
    return interaction;
  }

  public <T> ObjectSelectionView<T> addSelectionView(String label, AgnosticUIConfiguration<T> uiConfiguration) {
    FxObjectSelectionView<T> selectionView = new ComboBoxSelectionView<>(label, uiConfiguration);
    pane.add(selectionView.getNode(), new CC().growX());
    return selectionView;
  }

  public Node getNode() {
    return pane;
  }
}