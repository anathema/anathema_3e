package net.sf.anathema.hero.magic.spells;

import net.sf.anathema.hero.magic.basic.Magic;

public interface Spell extends Magic {

  CircleType getCircleType();

  String getTarget();
}