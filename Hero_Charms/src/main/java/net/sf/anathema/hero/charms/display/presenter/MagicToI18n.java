package net.sf.anathema.hero.charms.display.presenter;

import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.hero.magic.basic.Magic;

import java.util.function.Function;

public class MagicToI18n<M extends Magic> implements Function<M, String> {

  private Resources resources;

  public MagicToI18n(Resources resources) {
    this.resources = resources;
  }

  @Override
  public String apply(M m) {
    return resources.getString(m.getId());
  }
}
