package net.sf.anathema.hero.charms.display.coloring;

import net.sf.anathema.magic.data.Charm;
import net.sf.anathema.magic.data.prerequisite.CharmPrerequisite;
import net.sf.anathema.platform.tree.display.TreeView;

public interface CharmColoring {

  void colorCharm(Charm charm);

  void colorNonCharmPrerequisite(String nodeId, CharmPrerequisite prerequisite);

  void operateOn(TreeView treeView);
}