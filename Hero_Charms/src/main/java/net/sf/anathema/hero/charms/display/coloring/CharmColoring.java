package net.sf.anathema.hero.charms.display.coloring;

import net.sf.anathema.hero.magic.charm.Charm;
import net.sf.anathema.hero.magic.charm.prerequisite.IndirectCharmPrerequisite;
import net.sf.anathema.platform.tree.display.TreeView;

public interface CharmColoring {

  void colorCharm(Charm charm);

  void colorNonCharmPrerequisite(IndirectCharmPrerequisite prerequisite);

  void operateOn(TreeView treeView);
}