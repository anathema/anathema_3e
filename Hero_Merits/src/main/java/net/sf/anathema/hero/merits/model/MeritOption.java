package net.sf.anathema.hero.merits.model;

import java.util.List;

import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.merits.model.mechanics.MeritMechanicalDetail;
import net.sf.anathema.library.model.OptionalEntryReference;
import net.sf.anathema.library.model.trait.OptionalTraitOption;

public interface MeritOption extends OptionalTraitOption {

  int MAX_MERIT_RATING = 5;

  MeritCategory getCategory();

  boolean allowsRepurchase();

  boolean isHeroEligible(Hero hero);

  List<String> getContingentTraitTypes();

  List<MeritMechanicalDetail> getMechanics();

  boolean isReferencedBy(OptionalEntryReference reference);
}