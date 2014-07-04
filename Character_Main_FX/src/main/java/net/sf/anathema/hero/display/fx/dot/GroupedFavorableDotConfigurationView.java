package net.sf.anathema.hero.display.fx.dot;

import net.sf.anathema.hero.application.ColumnCount;

public interface GroupedFavorableDotConfigurationView {

  void initGui(ColumnCount columnCount);

  void startNewTraitGroup(String groupLabel);

  ExtensibleDotView addExtensibleTraitView(String string, int maximalValue);
}