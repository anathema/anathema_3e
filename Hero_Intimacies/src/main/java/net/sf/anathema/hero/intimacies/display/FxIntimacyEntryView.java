package net.sf.anathema.hero.intimacies.display;

import javafx.scene.Node;
import net.sf.anathema.hero.display.fx.configurableview.FxConfigurableSingleLineView;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.gui.AbstractUIConfiguration;
import net.sf.anathema.lib.gui.selection.ObjectSelectionView;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;
import net.sf.anathema.library.event.ObjectChangedListener;

public class FxIntimacyEntryView implements IntimacyEntryView {
  private final FxConfigurableSingleLineView view = new FxConfigurableSingleLineView();
  private final ITextView textView;

  public FxIntimacyEntryView(String labelText) {
    this.textView = view.addLineView(labelText);
  }

  @Override
  public void addTextChangeListener(ObjectChangedListener<String> listener) {
    textView.addTextChangedListener(listener);
  }

  public Tool addTool() {
    return view.addEditAction();
  }

  @Override
  public void clear() {
    textView.setText(null);
  }

  @Override
  public <T> ObjectSelectionView<T> addSelection() {
    return view.addSelectionView("", new AbstractUIConfiguration<T>() {
      @Override
      protected String labelForExistingValue(T value) {
        return value.toString();
      }
    });
  }

  public Node getNode() {
    return view.getNode();
  }
}