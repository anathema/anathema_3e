package net.sf.anathema.hero.charms.display.presenter;

import net.sf.anathema.hero.charms.model.CharmTree;

public interface CharmGroupInformer {
  boolean hasGroupSelected();

  CharmTree getCurrentTree();
}