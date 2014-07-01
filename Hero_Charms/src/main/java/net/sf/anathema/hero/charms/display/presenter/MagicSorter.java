package net.sf.anathema.hero.charms.display.presenter;

import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.hero.magic.basic.Magic;
import net.sf.anathema.lib.compare.StringSorter;

public class MagicSorter<M extends Magic> extends StringSorter<M> {

  public MagicSorter(Resources resources) {
    super(new MagicToI18n<>(resources));
  }
}
