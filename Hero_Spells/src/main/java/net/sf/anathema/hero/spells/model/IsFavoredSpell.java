package net.sf.anathema.hero.spells.model;

import net.sf.anathema.hero.charms.model.favored.FavoredChecker;
import net.sf.anathema.hero.magic.basic.Magic;
import net.sf.anathema.hero.magic.spells.Spell;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.traits.model.TraitModelFetcher;
import net.sf.anathema.hero.traits.model.TraitType;

public class IsFavoredSpell implements FavoredChecker {

  private Hero hero;

  public IsFavoredSpell(Hero hero) {
    this.hero = hero;
  }

  @Override
  public boolean supportsMagic(Magic magic) {
    return magic instanceof Spell;
  }

  @Override
  public boolean isFavored(Magic magic) {
    TraitType traitType = SpellsModelFetcher.fetch(hero).getFavoringTraitType();
    return TraitModelFetcher.fetch(hero).getTrait(traitType).isCasteOrFavored();
  }
}
