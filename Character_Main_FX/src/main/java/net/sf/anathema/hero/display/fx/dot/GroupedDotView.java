package net.sf.anathema.hero.display.fx.dot;

public interface GroupedDotView {
  void startNewGroup(String groupLabel);

  ExtensibleDotView addExtensibleTraitView(String labelText, int maxValue);
}
