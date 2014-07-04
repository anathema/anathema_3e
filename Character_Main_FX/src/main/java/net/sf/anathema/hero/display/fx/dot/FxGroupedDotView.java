package net.sf.anathema.hero.display.fx.dot;

import net.sf.anathema.hero.application.ColumnCount;
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
  public ExtensibleDotView addExtensibleTraitView(String labelText, int maxValue) {
    FxDotView view = FxDotView.WithDefaultLayout(labelText, maxValue);
    FxExtensibleDotView extensibleTraitView = new FxExtensibleDotView(view);
    extensibleTraitView.addTo(panel);
    return extensibleTraitView;
  }
}
