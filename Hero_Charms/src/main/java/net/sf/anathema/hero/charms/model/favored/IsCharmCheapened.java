package net.sf.anathema.hero.charms.model.favored;

import net.sf.anathema.hero.abilities.model.AbilitiesModelFetcher;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.magic.model.favored.CheapenedChecker;
import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.hero.traits.model.TraitTypeUtils;
import net.sf.anathema.magic.data.Charm;
import net.sf.anathema.magic.data.Magic;

public class IsCharmCheapened implements CheapenedChecker {
  private Hero hero;

  public IsCharmCheapened(Hero hero) {
    this.hero = hero;
  }

  @Override
  public boolean supportsMagic(Magic magic) {
    return magic instanceof Charm;
  }

  @Override
  public boolean isCheapened(Magic magic) {
    Charm charm = (Charm) magic;
    return isPrimaryTraitCheapened(charm);
  }

  private boolean isPrimaryTraitCheapened(Charm charm) {
    // todo (sandra) remodel that primary traits might not be abilities
    TraitType traitType = new TraitTypeUtils().getPrimaryTraitType(charm);
    if (AbilitiesModelFetcher.fetch(hero).contains(traitType)) {
    	return AbilitiesModelFetcher.fetch(hero).getState(traitType).isCheapened();
    }
    return false;
  }
}
