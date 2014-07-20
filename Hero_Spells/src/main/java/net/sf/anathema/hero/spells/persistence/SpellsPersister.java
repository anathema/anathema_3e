package net.sf.anathema.hero.spells.persistence;

import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.individual.persistence.AbstractModelJsonPersister;
import net.sf.anathema.hero.spells.data.Spell;
import net.sf.anathema.hero.spells.data.Spells;
import net.sf.anathema.hero.spells.model.SpellsModel;
import net.sf.anathema.library.identifier.Identifier;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SuppressWarnings("UnusedDeclaration")
public class SpellsPersister extends AbstractModelJsonPersister<SpellListPto, SpellsModel> {

  public SpellsPersister() {
    super("spells", SpellListPto.class);
  }

  @Override
  public Identifier getModelId() {
    return SpellsModel.ID;
  }

  @Override
  protected void loadModelFromPto(Hero hero, SpellsModel model, SpellListPto pto) {
    Spells creationSpellList = collectSpells(model, pto, spell -> !spell.isExperienceLearned);
    model.addSpells(creationSpellList, false);
    Spells experienceSpellList = collectSpells(model, pto, spell -> spell.isExperienceLearned);
    model.addSpells(experienceSpellList, true);
  }

  private Spells collectSpells(SpellsModel model, SpellListPto pto, Predicate<AttributedPto> predicate) {
    Stream<AttributedPto> spells = pto.spells.stream();
    Collection<AttributedPto> matching = spells.filter(predicate).collect(Collectors.toList());
    return collectSpells(model, matching);
  }

  private Spells collectSpells(SpellsModel model, Iterable<AttributedPto> matchingPtoList) {
    Spells found = new Spells();
    for (AttributedPto spellPto : matchingPtoList) {
      Spell spell = model.getSpellById(spellPto.id);
      found.add(spell);
    }
    return found;
  }

  @Override
  protected SpellListPto saveModelToPto(SpellsModel model) {
    SpellListPto pto = new SpellListPto();
    for (Spell spell : model.getLearnedSpells()) {
      AttributedPto spellPto = createSpellPto(model, spell);
      pto.spells.add(spellPto);
    }
    return pto;
  }

  private AttributedPto createSpellPto(SpellsModel model, Spell spell) {
    AttributedPto spellPto = new AttributedPto();
    spellPto.id = spell.getName().text;
    spellPto.isExperienceLearned = !model.isLearnedOnCreation(spell);
    return spellPto;
  }
}
