package net.sf.anathema.hero.charms.compiler.special;

import net.sf.anathema.charm.data.reference.CharmName;
import net.sf.anathema.charm.template.special.Requirement;
import net.sf.anathema.charm.template.special.SpecialCharmTemplate;
import net.sf.anathema.charm.template.special.TierRepurchase;
import net.sf.anathema.charm.template.special.TraitRepurchase;
import net.sf.anathema.hero.charms.model.special.multilearn.CharmTier;
import net.sf.anathema.hero.charms.model.special.multilearn.EssenceFixedMultiLearnableCharm;
import net.sf.anathema.hero.charms.model.special.ISpecialCharm;
import net.sf.anathema.hero.charms.model.special.multilearn.StaticMultiLearnableCharm;
import net.sf.anathema.hero.charms.model.special.multilearn.TieredMultiLearnableCharm;
import net.sf.anathema.hero.charms.model.special.multilearn.TraitCharmTier;
import net.sf.anathema.hero.charms.model.special.multilearn.TraitDependentMultiLearnableCharm;
import net.sf.anathema.hero.traits.TraitTypeFinder;
import net.sf.anathema.charm.template.special.Repurchase;
import net.sf.anathema.charm.template.special.Tier;
import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.hero.traits.model.types.ValuedTraitType;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("UnusedDeclaration")
public class RepurchaseCharmBuilder implements SpecialCharmBuilder {

  private final TraitTypeFinder traitTypeFinder = new TraitTypeFinder();

  @Override
  public ISpecialCharm readCharm(SpecialCharmTemplate overallDto) {
    Repurchase repurchase = overallDto.repurchase;
    if (repurchase.isEssenceRepurchase) {
      return new EssenceFixedMultiLearnableCharm(new CharmName(overallDto.charmId));
    }
    CharmName charmName = new CharmName(overallDto.charmId);
    if (repurchase.traitRepurchase != null) {
      return createTraitMultiLearnable(charmName, repurchase.traitRepurchase);
    }
    if (repurchase.staticRepurchase != null) {
      return new StaticMultiLearnableCharm(charmName, repurchase.staticRepurchase.limit);
    }
    return createTierMultiLearnable(charmName, repurchase);
  }

  private ISpecialCharm createTierMultiLearnable(CharmName id, Repurchase repurchase) {
    TierRepurchase dto = repurchase.tierRepurchase;
    List<CharmTier> tiers = new ArrayList<>();
    for (Tier tier : dto.tiers) {
      tiers.add(createTier(tier));
    }
    return new TieredMultiLearnableCharm(id, tiers.toArray(new CharmTier[tiers.size()]));
  }

  private CharmTier createTier(Tier dto) {
    TraitCharmTier traitCharmTier = new TraitCharmTier();
    for (Requirement requirement : dto.requirements) {
      TraitType traitType = traitTypeFinder.getTrait(requirement.traitType);
      traitCharmTier.addRequirement(new ValuedTraitType(traitType, requirement.traitValue));
    }
    return traitCharmTier;
  }

  private ISpecialCharm createTraitMultiLearnable(CharmName id, TraitRepurchase dto) {
    TraitType trait = traitTypeFinder.getTrait(dto.limitingTrait);
    int modifier = dto.modifier;
    int absoluteMax = dto.absoluteMax;
    return new TraitDependentMultiLearnableCharm(id, absoluteMax, trait, modifier);
  }

  @Override
  public boolean supports(SpecialCharmTemplate overallDto) {
    return overallDto.repurchase != null;
  }
}