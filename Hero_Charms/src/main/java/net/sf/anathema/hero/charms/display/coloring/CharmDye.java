package net.sf.anathema.hero.charms.display.coloring;

import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.platform.tree.display.TreeView;

public interface CharmDye {

  void colorCharm(Charm charm);

  void setCharmVisuals();

  void operateOn(TreeView treeView);
}