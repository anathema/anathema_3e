package net.sf.anathema.library.view.fx;

import javafx.scene.Node;
import net.sf.anathema.library.fx.configurableview.FxConfigurableSingleLineView;
import net.sf.anathema.library.interaction.model.Tool;
import net.sf.anathema.library.presenter.AgnosticUIConfiguration;
import net.sf.anathema.library.text.ITextView;
import net.sf.anathema.library.view.ObjectSelectionView;
import net.sf.anathema.library.view.OptionalTraitEntryView;

public class FxOptionalTraitsEntryView implements OptionalTraitEntryView {
  private final FxConfigurableSingleLineView view = new FxConfigurableSingleLineView();

  public Tool addTool() {
    return view.addEditAction();
  }

  @Override
  public <T> ObjectSelectionView<T> addSelection(AgnosticUIConfiguration<T> uiConfiguration) {
    return view.addSelectionView("", uiConfiguration);
  }

  public Node getNode() {
    return view.getNode();
  }

  @Override
  public ITextView addDescriptionBox(String label) {
    return view.addLineView(label);
  }
}