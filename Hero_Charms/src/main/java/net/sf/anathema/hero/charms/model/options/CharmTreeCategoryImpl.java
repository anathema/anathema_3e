package net.sf.anathema.hero.charms.model.options;

import net.sf.anathema.hero.charms.compiler.CharmProvider;
import net.sf.anathema.hero.framework.type.CharacterType;
import net.sf.anathema.hero.magic.charm.Charm;

public class CharmTreeCategoryImpl extends AbstractCharmTreeCategory {

  public CharmTreeCategoryImpl(CharmProvider provider, CharacterType characterType) {
    super(provider.getCharms(characterType));
  }

  @Override
  public boolean isLearnable(Charm charm) {
    return true;
  }
}