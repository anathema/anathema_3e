package net.sf.anathema.hero.spells.parser;

import net.sf.anathema.hero.spells.data.Spell;
import net.sf.anathema.hero.spells.data.Spells;

import java.util.ArrayList;
import java.util.List;

public class ListSpellCache implements SpellCache {
  private List<Spell> spellList = new ArrayList<>();

  public void addSpell(Spell spell) {
    spellList.add(spell);
  }

  @Override
  public Spells getSpells() {
    return Spells.copyOf(spellList);
  }
}
