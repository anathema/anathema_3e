package net.sf.anathema.hero.spells;

import net.sf.anathema.hero.charms.model.CharmsModel;
import net.sf.anathema.hero.charms.model.learn.MagicLearner;
import net.sf.anathema.hero.environment.HeroEnvironment;
import net.sf.anathema.hero.individual.change.ChangeAnnouncer;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.spells.data.CircleType;
import net.sf.anathema.hero.spells.data.Spell;
import net.sf.anathema.hero.spells.data.Spells;
import net.sf.anathema.hero.spells.model.SpellsModel;
import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.hero.traits.model.types.AbilityType;
import net.sf.anathema.library.event.ChangeListener;
import net.sf.anathema.library.exception.NotYetImplementedException;
import net.sf.anathema.library.identifier.Identifier;
import net.sf.anathema.magic.data.Magic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class DummySpellsModel implements SpellsModel {

  private List<Spell> spells = new ArrayList<>();

  @Override
  public Identifier getId() {
    return ID;
  }

  @Override
  public void initialize(HeroEnvironment environment, Hero hero) {
    // nothing to do
  }

  @Override
  public void initializeListening(ChangeAnnouncer announcer) {
    // nothing to do
  }

  @Override
  public void removeSpells(Spells removedSpells) {
    throw new NotYetImplementedException();
  }

  @Override
  public void addSpells(Spells addedSpells) {
    spells.addAll(addedSpells.asList());
  }

  @Override
  public Spells getLearnedSpells() {
    throw new NotYetImplementedException();
  }

  @Override
  public void addChangeListener(ChangeListener listener) {
    throw new NotYetImplementedException();
  }

  @Override
  public boolean isSpellAllowed(Spell spell) {
    throw new NotYetImplementedException();
  }


  @Override
  public Spell getSpellById(String string) {
    throw new NotYetImplementedException();
  }

  @Override
  public boolean isLearnedOnCreation(Spell spell) {
    throw new NotYetImplementedException();
  }

  @Override
  public Spells getLearnedSpells(boolean experienced) {
    if (experienced) {
      throw new IllegalArgumentException("Not implemented");
    }
    Spells spellSet = new Spells();
    spellSet.addAll(spells);
    return spellSet;
  }

  @Override
  public void addSpells(Spells addedSpells, boolean experienced) {
    if (experienced) {
      throw new IllegalArgumentException("Not implemented");
    }
    spells.addAll(addedSpells.asList());
  }

  @Override
  public void removeSpells(Spells removedSpells, boolean experienced) {
    if (experienced) {
      throw new IllegalArgumentException("Not implemented");
    }
    spells.removeAll(removedSpells.asList());
  }

  @Override
  public boolean isSpellAllowed(Spell spell, boolean experienced) {
    throw new NotYetImplementedException();
  }

  @Override
  public boolean isLearnedOnCreationOrExperience(Spell spell) {
    return false;
  }

  @Override
  public Spells getAvailableSpellsInCircle(CircleType circle) {
    throw new NotYetImplementedException();
  }

  @Override
  public Spells getLearnedSpellsInCircles(Collection<CircleType> eligibleCircles) {
    Spells spells = new Spells();
    for (Spell spell : getLearnedSpells()) {
      if (eligibleCircles.contains(spell.getCircleType())) {
        spells.add(spell);
      }
    }
    return spells;
  }

  @Override
  public boolean canLearnSorcery() {
    return false;
  }

  @Override
  public boolean canLearnNecromancy() {
    return false;
  }

  @Override
  public Collection<CircleType> getNecromancyCircles() {
    return Collections.emptyList();
  }

  @Override
  public Collection<CircleType> getSorceryCircles() {
    return Collections.emptyList();
  }

  @Override
  public TraitType getFavoringTraitType() {
    return AbilityType.Occult;
  }

  public void initializeMagicModel(CharmsModel charmsModel) {
    charmsModel.addLearnProvider(new MagicLearner() {
      @Override
      public boolean handlesMagic(Magic magic) {
        return magic instanceof Spell;
      }

      @Override
      public int getAdditionalBonusPoints(Magic magic) {
        return 0;
      }

      @Override
      public int getCreationLearnCount(Magic magic) {
        return 1;
      }

      @Override
      public Collection<? extends Magic> getLearnedMagic(boolean experienced) {
        return getLearnedSpells(experienced).asList();
      }
    });
  }
}