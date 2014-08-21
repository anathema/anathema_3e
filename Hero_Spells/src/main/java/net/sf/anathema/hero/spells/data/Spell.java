package net.sf.anathema.hero.spells.data;

import java.util.List;

import net.sf.anathema.charm.data.reference.SpellName;
import net.sf.anathema.magic.data.Magic;

public interface Spell extends Magic {

  SpellName getName();

  CircleType getCircleType();

  String getDuration();
  
  List<String> getKeywords();
}