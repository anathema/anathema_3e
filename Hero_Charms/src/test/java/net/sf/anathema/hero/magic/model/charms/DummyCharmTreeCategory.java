package net.sf.anathema.hero.magic.model.charms;

import net.sf.anathema.charm.data.reference.TreeCategory;
import net.sf.anathema.hero.charms.model.options.AbstractCharmTreeCategory;
import net.sf.anathema.hero.magic.charm.Charm;

public class DummyCharmTreeCategory extends AbstractCharmTreeCategory {

  public DummyCharmTreeCategory(TreeCategory category, Charm... charms) {
    super(charms, category);
  }

  @Override
  public boolean isLearnable(Charm charm) {
    return true;
  }
}
