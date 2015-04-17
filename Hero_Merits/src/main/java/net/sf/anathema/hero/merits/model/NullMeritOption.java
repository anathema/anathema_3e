package net.sf.anathema.hero.merits.model;

import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.merits.model.mechanics.MechanicalDetail;
import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.library.model.OptionalEntryReference;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class NullMeritOption implements MeritOption {
  @Override
  public MeritCategory getCategory() {
    return MeritCategory.Innate;
  }

  @Override
  public boolean allowsRepurchase() {
    return false;
  }

  @Override
  public boolean isHeroEligible(Hero hero) {
    return false;
  }

  @Override
  public boolean isLegalValue(int value) {
    return false;
  }

  @Override
  public List<String> getContingentTraitTypes() {
    return Collections.emptyList();
  }

  @Override
  public List<MechanicalDetail> getMechanics() {
    return Collections.emptyList();
  }

  @Override
  public int getMinimumValue() {
    return 0;
  }

  @Override
  public int getMaximumValue() {
    return 0;
  }

  @Override
  public TraitType getTraitType() {
    return new TraitType("NotAMeritOption");
  }

  @Override
  public boolean isReferencedBy(OptionalEntryReference reference) {
    return false;
  }

  @Override
  public Collection<String> getSuggestions() {
    return Collections.emptyList();
  }
}