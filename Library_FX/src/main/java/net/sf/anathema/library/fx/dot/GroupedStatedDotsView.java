package net.sf.anathema.library.fx.dot;

public interface GroupedStatedDotsView {

  void initGui(ColumnCount columnCount);

  void startNewTraitGroup(String groupLabel);

  ExtensibleDotView addExtensibleTraitView(String string, int maximalValue);
}