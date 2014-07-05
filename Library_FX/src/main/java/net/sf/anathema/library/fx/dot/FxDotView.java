package net.sf.anathema.library.fx.dot;

import javafx.scene.control.Label;
import net.sf.anathema.library.event.IntegerChangedListener;
import net.sf.anathema.library.view.IntValueView;
import org.jmock.example.announcer.Announcer;
import org.tbee.javafx.scene.layout.MigPane;

public class FxDotView implements IntValueView {
  public static FxDotView WithDefaultLayout(String labelText, int maxValue) {
    return new FxDotView(labelText, maxValue, FxConfigurableLayout.Right());
  }

  public static FxDotView AsSingleNode(String labelText, int maxValue) {
    return new FxDotView(labelText, maxValue, FxConfigurableLayout.Single());
  }

  private final Announcer<IntegerChangedListener> valueChangeAnnouncer = new Announcer<>(IntegerChangedListener.class);
  private final DotSelectionSpinner spinner;
  private final Label label;

  private final FxConfigurableLayout layout;

  private FxDotView(String labelText, int maxValue, FxConfigurableLayout layout) {
    this.layout = layout;
    this.label = new Label(labelText);
    this.spinner = new DotSelectionSpinner(0, maxValue);
    initListening();
  }

  @Override
  public void setValue(int newValue) {
    spinner.setValueSilently(newValue);
  }

  @Override
  public void addIntValueChangedListener(IntegerChangedListener listener) {
    valueChangeAnnouncer.addListener(listener);
  }

  @Override
  public void removeIntValueChangedListener(IntegerChangedListener listener) {
    valueChangeAnnouncer.removeListener(listener);
  }

  public void addTo(MigPane parent) {
    layout.addLabel(label);
    layout.addDisplay(spinner.getNode());
    layout.addTo(parent);
  }

  private void initListening() {
    spinner.addListener((observableValue, oldValue, newValue) -> {
      spinner.setValueSilently(oldValue);
      valueChangeAnnouncer.announce().valueChanged(newValue);
    });
  }
}