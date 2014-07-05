package net.sf.anathema.hero.charms.model.favored;

import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.hero.abilities.model.AbilitiesModel;
import net.sf.anathema.hero.abilities.model.AbilitiesModelFetcher;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.traits.model.Trait;
import net.sf.anathema.hero.traits.model.TraitModelFetcher;
import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.hero.traits.model.TraitTypeUtils;
import net.sf.anathema.magic.data.Magic;

import static net.sf.anathema.charm.data.martial.MartialArtsUtilities.isMartialArts;
import static net.sf.anathema.hero.traits.model.types.AbilityType.MartialArts;

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
    return isCheapened(charm) || isPrimaryTraitCheapened(charm);
  }

  private boolean isCheapened(Charm charm) {
    AbilitiesModel abilities = AbilitiesModelFetcher.fetch(hero);
    return isMartialArts(charm) && abilities.getState(MartialArts).isCasteOrFavored();
  }

  private boolean isPrimaryTraitCheapened(Charm charm) {
    // todo (sandra) remodel that primary traits might not be abilities
    TraitType traitType = new TraitTypeUtils().getPrimaryTraitType(charm);
    Trait primaryTrait = getTrait(traitType);
    return AbilitiesModelFetcher.fetch(hero).getState(traitType).isCasteOrFavored();
  }

  private Trait getTrait(TraitType traitType) {
    return TraitModelFetcher.fetch(hero).getTrait(traitType);
  }
}
