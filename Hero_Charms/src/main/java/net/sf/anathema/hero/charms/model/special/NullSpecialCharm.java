package net.sf.anathema.hero.charms.model.special;

import net.sf.anathema.magic.data.reference.CharmName;

public class NullSpecialCharm implements CharmSpecialLearning {
  @Override
  public void accept(ICharmSpecialLearningVisitor visitor) {
    //nothing to do
  }

  @Override
  public CharmName getCharmName() {
    return new CharmName("");
  }
}