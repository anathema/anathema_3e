package net.sf.anathema.hero.magic.charm.type;

import net.sf.anathema.lib.util.Identifier;

public enum TurnType implements Identifier {

  Tick, LongTick, DramaticAction;

  @Override
  public String getId() {
    return name();
  }
}