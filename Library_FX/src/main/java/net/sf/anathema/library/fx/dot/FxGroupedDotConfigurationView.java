package net.sf.anathema.library.fx.dot;

import javafx.scene.Node;

import net.sf.anathema.library.fx.NodeHolder;
import net.sf.anathema.library.fx.layout.LayoutUtils;

import org.tbee.javafx.scene.layout.MigPane;

public class FxGroupedDotConfigurationView implements GroupedStatedDotsView, NodeHolder {
  private FxGroupedDotView groupedView;
  private final MigPane pane = new MigPane(LayoutUtils.withoutInsets());

  @Override
  public void initGui(ColumnCount columnCount) {
    this.groupedView = new FxGroupedDotView(pane, columnCount);
  }

  @Override
  public void startNewTraitGroup(String groupLabel) {
    groupedView.startNewGroup(groupLabel);
  }

  @Override
  public ExtensibleDotView addExtensibleTraitView(String string, int maximalValue, boolean derived) {
    return groupedView.addExtensibleTraitView(string, maximalValue, derived);
  }

  @Override
  public Node getNode() {
    return pane;
  }
}
