package net.sf.anathema.hero.spells.model;

import net.sf.anathema.hero.abilities.model.AbilitiesModelFetcher;
import net.sf.anathema.hero.charms.model.favored.CheapenedChecker;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.spells.data.Spell;
import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.hero.traits.model.state.TraitState;
import net.sf.anathema.magic.data.Magic;

public class IsFavoredSpell implements CheapenedChecker {

  private Hero hero;

  public IsFavoredSpell(Hero hero) {
    this.hero = hero;
  }

  @Override
  public boolean supportsMagic(Magic magic) {
    return magic instanceof Spell;
  }

  @Override
  public boolean isCheapened(Magic magic) {
    TraitType traitType = SpellsModelFetcher.fetch(hero).getFavoringTraitType();
    // todo (sandra) model that spells might be favored non-ability
    TraitState traitState = AbilitiesModelFetcher.fetch(hero).getState(traitType);
    return traitState.isCasteOrFavored();
  }
}
