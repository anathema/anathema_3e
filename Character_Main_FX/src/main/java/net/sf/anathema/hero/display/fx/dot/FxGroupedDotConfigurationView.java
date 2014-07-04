package net.sf.anathema.hero.display.fx.dot;

import javafx.scene.Node;
import net.sf.anathema.hero.application.ColumnCount;
import net.sf.anathema.library.fx.NodeHolder;
import org.tbee.javafx.scene.layout.MigPane;

public class FxGroupedDotConfigurationView implements GroupedFavorableDotConfigurationView, NodeHolder {
  private FxGroupedDotView groupedView;
  private final MigPane pane = new MigPane();

  @Override
  public void initGui(ColumnCount columnCount) {
    this.groupedView = new FxGroupedDotView(pane, columnCount);
  }

  @Override
  public void startNewTraitGroup(String groupLabel) {
    groupedView.startNewGroup(groupLabel);
  }

  @Override
  public ExtensibleDotView addExtensibleTraitView(String string, int maximalValue) {
    return groupedView.addExtensibleTraitView(string, maximalValue);
  }

  @Override
  public Node getNode() {
    return pane;
  }
}
