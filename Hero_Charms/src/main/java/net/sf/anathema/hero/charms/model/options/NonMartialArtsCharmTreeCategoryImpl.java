package net.sf.anathema.hero.charms.model.options;

import net.sf.anathema.charm.data.reference.TreeCategory;
import net.sf.anathema.hero.charms.compiler.CharmProvider;
import net.sf.anathema.hero.framework.type.CharacterType;
import net.sf.anathema.hero.magic.charm.Charm;

public class NonMartialArtsCharmTreeCategoryImpl extends AbstractCharmTreeCategory {

  public NonMartialArtsCharmTreeCategoryImpl(CharmProvider provider, CharacterType characterType) {
    super(provider.getCharms(characterType), new TreeCategory(characterType.getId()));
  }

  @Override
  public boolean isLearnable(Charm charm) {
    return true;
  }
}