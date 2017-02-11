package net.sf.anathema.library.fx.dot;

public interface GroupedDotView {
  void startNewGroup(String groupLabel);

  /**
   * @param derived if true, the value shown is a derived value and should not be user-editable.
   */
  ExtensibleDotView addExtensibleTraitView(String labelText, int maxValue, boolean derived);
}
