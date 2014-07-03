package net.sf.anathema.hero.charms.sheet.content.stats;

import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.hero.charms.sheet.content.IMagicStats;

public class MultipleEffectCharmStats extends AbstractCharmStats implements IMagicStats {

  private final String effect;

  public MultipleEffectCharmStats(Charm charm, String effect) {
    super(charm);
    this.effect = effect;
  }

  @Override
  public String getNameString(Resources resources) {
    String effectString = resources.getString(getMagic().getName().text + ".Subeffects." + effect);
    return resources.getString(getMagic().getName().text + ".PrintPattern", effectString);
  }
}
