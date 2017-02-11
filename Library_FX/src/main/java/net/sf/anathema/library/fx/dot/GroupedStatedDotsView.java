package net.sf.anathema.library.fx.dot;

public interface GroupedStatedDotsView {

  void initGui(ColumnCount columnCount);

  void startNewTraitGroup(String groupLabel);

  /**
   * @param derived if true, the value shown is a derived value and should not be user-editable.
   */
  ExtensibleDotView addExtensibleTraitView(String string, int maximalValue, boolean derived);
}