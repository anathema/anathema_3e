package net.sf.anathema.hero.charms.model.options;

import net.sf.anathema.hero.charms.compiler.CharmProvider;
import net.sf.anathema.hero.magic.charm.Charm;
import net.sf.anathema.hero.magic.charm.martial.MartialArtsLevel;
import net.sf.anathema.hero.magic.charm.martial.MartialArtsUtilities;

import java.text.MessageFormat;

import static java.text.MessageFormat.format;

public class MartialArtsCharmTreeCategory extends AbstractCharmTreeCategory {

  private final MartialArtsLevel standardLevel;

  public MartialArtsCharmTreeCategory(CharmProvider charmProvider, MartialArtsLevel standardLevel) {
    super(charmProvider.getMartialArtsCharms());
    this.standardLevel = standardLevel;
  }

  @Override
  public boolean isLearnable(Charm charm) {
    MartialArtsLevel level = MartialArtsUtilities.getLevel(charm);
    if (level == null) {
      String pattern = "The charm {0} is not a Martial Arts charm.\nTry naming Martial Arts as the first prerequisite trait.";
      String format = format(pattern, charm.getId());
      throw new IllegalStateException(format);
    }
    return level.compareTo(standardLevel) <= 1;
  }
}