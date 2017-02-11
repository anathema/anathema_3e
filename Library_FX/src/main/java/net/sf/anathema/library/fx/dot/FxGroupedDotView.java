package net.sf.anathema.library.fx.dot;

import org.tbee.javafx.scene.layout.MigPane;

public class FxGroupedDotView implements GroupedDotView {
  private final FxGroupedColumnPanel panel;

  public FxGroupedDotView(MigPane pane, ColumnCount columnCount) {
    this.panel = new FxGroupedColumnPanel(pane, columnCount);
  }

  @Override
  public void startNewGroup(String groupLabel) {
    panel.startNewGroup(groupLabel);
  }

  @Override
  public ExtensibleDotView addExtensibleTraitView(String labelText, int maxValue, boolean derived) {
    FxDotView view = FxDotView.WithDefaultLayout(labelText, maxValue);
    if (derived) {
      view.disableUserInput();
    }
    FxExtensibleDotView extensibleTraitView = new FxExtensibleDotView(view);
    extensibleTraitView.addTo(panel);
    return extensibleTraitView;
  }
}
