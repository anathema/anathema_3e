package net.sf.anathema.hero.magic.spells;

import net.sf.anathema.charm.data.reference.SpellName;
import net.sf.anathema.hero.magic.basic.Magic;

public interface Spell extends Magic {

  SpellName getName();

  CircleType getCircleType();

  String getTarget();
}