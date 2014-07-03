package net.sf.anathema.fx.hero.traitview;

import net.sf.anathema.hero.display.ExtensibleTraitView;

public interface GroupedTraitView {
  void startNewGroup(String groupLabel);

  ExtensibleTraitView addExtensibleTraitView(String labelText, int maxValue);
}
