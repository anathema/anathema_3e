package net.sf.anathema.hero.thaumaturgy.display.view;

import javafx.scene.Node;
import net.sf.anathema.hero.thaumaturgy.display.presenter.RitualEntryView;
import net.sf.anathema.library.fx.configurableview.FxConfigurableSingleLineView;
import net.sf.anathema.library.interaction.model.Tool;
import net.sf.anathema.library.presenter.AgnosticUIConfiguration;
import net.sf.anathema.library.text.ITextView;
import net.sf.anathema.library.view.ObjectSelectionView;

public class FxRitualsEntryView implements RitualEntryView {
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