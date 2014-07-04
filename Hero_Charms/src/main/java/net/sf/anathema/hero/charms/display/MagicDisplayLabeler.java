package net.sf.anathema.hero.charms.display;

import net.sf.anathema.library.resources.Resources;
import net.sf.anathema.magic.data.Magic;

public class MagicDisplayLabeler {
  private Resources resources;

  public MagicDisplayLabeler(Resources resources) {
    this.resources = resources;
  }

  public String getLabelForMagic(Magic magic) {
    return resources.getString(magic.getName().text);
  }

  public boolean supportsMagic(Magic magic) {
    if (magic == null) {
      return false;
    }
    return resources.supportsKey(magic.getName().text);
  }
}
