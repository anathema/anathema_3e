package net.sf.anathema.library.fx.dot;

public interface GroupedDotView {
  void startNewGroup(String groupLabel);

  ExtensibleDotView addExtensibleTraitView(String labelText, int maxValue);
}
