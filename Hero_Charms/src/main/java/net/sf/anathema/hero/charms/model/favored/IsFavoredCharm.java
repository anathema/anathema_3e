package net.sf.anathema.hero.charms.model.favored;

import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.traits.model.Trait;
import net.sf.anathema.hero.traits.model.TraitModelFetcher;
import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.hero.traits.model.TraitTypeUtils;
import net.sf.anathema.magic.data.Magic;

import static net.sf.anathema.charm.data.martial.MartialArtsUtilities.isMartialArts;
import static net.sf.anathema.hero.traits.model.types.AbilityType.MartialArts;

public class IsFavoredCharm implements FavoredChecker {
  private Hero hero;

  public IsFavoredCharm(Hero hero) {
    this.hero = hero;
  }

  @Override
  public boolean supportsMagic(Magic magic) {
    return magic instanceof Charm;
  }

  @Override
  public boolean isFavored(Magic magic) {
    Charm charm = (Charm) magic;
    return isFavoredMartialArts(charm) || isPrimaryTraitFavored(charm);
  }

  private boolean isFavoredMartialArts(Charm charm) {
    return isMartialArts(charm) && getTrait(MartialArts).isCasteOrFavored();
  }

  private boolean isPrimaryTraitFavored(Charm charm) {
    TraitType traitType = new TraitTypeUtils().getPrimaryTraitType(charm);
    Trait primaryTrait = getTrait(traitType);
    return primaryTrait.isCasteOrFavored();
  }

  private Trait getTrait(TraitType traitType) {
    return TraitModelFetcher.fetch(hero).getTrait(traitType);
  }
}
