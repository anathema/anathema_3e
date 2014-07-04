package net.sf.anathema.hero.charms.display.presenter;

import net.sf.anathema.lib.compare.StringSorter;
import net.sf.anathema.library.resources.Resources;
import net.sf.anathema.magic.data.Magic;

public class MagicSorter<M extends Magic> extends StringSorter<M> {

  public MagicSorter(Resources resources) {
    super(new MagicToI18n<>(resources));
  }
}
