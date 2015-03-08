package net.sf.anathema.hero.spells.data;

import net.sf.anathema.magic.data.reference.SpellName;
import net.sf.anathema.magic.data.Magic;

import java.util.List;

public interface Spell extends Magic {

  SpellName getName();

  CircleType getCircleType();

  String getDuration();
  
  List<String> getKeywords();
}