package net.sf.anathema.hero.spells.model;

import net.sf.anathema.hero.individual.model.HeroModel;
import net.sf.anathema.hero.spells.data.CircleType;
import net.sf.anathema.hero.spells.data.Spell;
import net.sf.anathema.hero.spells.data.Spells;
import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.library.event.ChangeListener;
import net.sf.anathema.library.identifier.Identifier;
import net.sf.anathema.library.identifier.SimpleIdentifier;

import java.util.Collection;
import java.util.List;

public interface SpellsModel extends HeroModel {

  Identifier ID = new SimpleIdentifier("Spells");

  void removeSpells(Spells removedSpells);

  void addSpells(Spells addedSpells);

  Spells getLearnedSpells();

  void addChangeListener(ChangeListener listener);

  boolean isSpellAllowed(Spell spell);

  Spell getSpellById(String string);

  boolean isLearnedOnCreation(Spell spell);

  Spells getLearnedSpells(boolean experienced);

  void addSpells(Spells addedSpells, boolean experienced);

  void removeSpells(Spells removedSpells, boolean experienced);

  boolean isSpellAllowed(Spell spell, boolean experienced);

  boolean isLearnedOnCreationOrExperience(Spell spell);

  Spells getAvailableSpellsInCircle(CircleType circle);

  Spells getLearnedSpellsInCircles(Collection<CircleType> eligibleCircles);

  boolean canLearnSorcery();

  boolean canLearnNecromancy();

  Collection<CircleType> getNecromancyCircles();

  Collection<CircleType> getSorceryCircles();

  TraitType getFavoringTraitType();
}