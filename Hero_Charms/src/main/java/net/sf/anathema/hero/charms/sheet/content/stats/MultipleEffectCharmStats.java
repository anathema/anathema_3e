package net.sf.anathema.hero.charms.sheet.content.stats;

import net.sf.anathema.library.resources.Resources;
import net.sf.anathema.magic.data.Charm;

public class MultipleEffectCharmStats extends AbstractCharmStats {

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
