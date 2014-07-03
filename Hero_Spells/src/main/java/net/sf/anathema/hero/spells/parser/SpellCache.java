package net.sf.anathema.hero.spells.parser;

import net.sf.anathema.hero.spells.data.Spell;

import java.util.ArrayList;
import java.util.List;

public class SpellCache implements ISpellCache {
  private List<Spell> spellList = new ArrayList<>();

  public void addSpell(Spell spell) {
    spellList.add(spell);
  }

  @Override
  public Spell[] getSpells() {
    return spellList.toArray(new Spell[spellList.size()]);
  }
}
