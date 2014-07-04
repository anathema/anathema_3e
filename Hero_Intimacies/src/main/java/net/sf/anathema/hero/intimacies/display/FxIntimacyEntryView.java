package net.sf.anathema.hero.intimacies.display;

import javafx.scene.Node;
import net.sf.anathema.hero.display.fx.configurableview.FxConfigurableSingleLineView;
import net.sf.anathema.library.event.ObjectValueListener;
import net.sf.anathema.library.interaction.model.Tool;
import net.sf.anathema.library.presenter.AbstractUIConfiguration;
import net.sf.anathema.library.text.ITextView;
import net.sf.anathema.library.view.ObjectSelectionView;

public class FxIntimacyEntryView implements IntimacyEntryView {
  private final FxConfigurableSingleLineView view = new FxConfigurableSingleLineView();
  private final ITextView textView;

  public FxIntimacyEntryView(String labelText) {
    this.textView = view.addLineView(labelText);
  }

  @Override
  public void addTextChangeListener(ObjectValueListener<String> listener) {
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