package net.sf.anathema.hero.charms.model.special;

import net.sf.anathema.charm.data.reference.CharmName;

public class NullSpecialCharm implements ISpecialCharm {
  @Override
  public void accept(ISpecialCharmVisitor visitor) {
    //nothing to do
  }

  @Override
  public CharmName getCharmName() {
    return new CharmName("");
  }
}