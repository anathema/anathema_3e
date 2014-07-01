package net.sf.anathema.hero.charms.display;

import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.hero.magic.basic.Magic;

public class MagicDisplayLabeler {
  private Resources resources;

  public MagicDisplayLabeler(Resources resources) {
    this.resources = resources;
  }

  public String getLabelForMagic(Magic magic) {
    return resources.getString(magic.getMagicName().text);
  }

  public boolean supportsMagic(Magic magic) {
    if (magic == null) {
      return false;
    }
    return resources.supportsKey(magic.getMagicName().text);
  }
}
