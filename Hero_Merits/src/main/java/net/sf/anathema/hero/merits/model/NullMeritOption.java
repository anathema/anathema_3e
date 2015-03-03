package net.sf.anathema.hero.merits.model;

import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.merits.model.mechanics.MeritMechanicalDetail;
import net.sf.anathema.hero.traits.model.types.ITraitTypeVisitor;
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
  public List<MeritMechanicalDetail> getMechanics() {
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
  public boolean isReferencedBy(OptionalEntryReference reference) {
    return false;
  }

  @Override
  public Collection<String> getSuggestions() {
    return Collections.emptyList();
  }

  @Override
  public void accept(ITraitTypeVisitor visitor) {
    //nothing to do
  }

  @Override
  public String getId() {
    return "NotAMeritOption";
  }
}