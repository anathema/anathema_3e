package net.sf.anathema.hero.charms.compiler.special;

import net.sf.anathema.charm.data.reference.CharmName;
import net.sf.anathema.hero.charms.model.special.multilearn.CharmTier;
import net.sf.anathema.hero.charms.model.special.multilearn.EssenceFixedMultiLearnableCharm;
import net.sf.anathema.hero.charms.model.special.ISpecialCharm;
import net.sf.anathema.hero.charms.model.special.multilearn.StaticMultiLearnableCharm;
import net.sf.anathema.hero.charms.model.special.multilearn.TieredMultiLearnableCharm;
import net.sf.anathema.hero.charms.model.special.multilearn.TraitCharmTier;
import net.sf.anathema.hero.charms.model.special.multilearn.TraitDependentMultiLearnableCharm;
import net.sf.anathema.hero.traits.TraitTypeFinder;
import net.sf.anathema.hero.magic.parser.dto.special.RepurchaseDto;
import net.sf.anathema.hero.magic.parser.dto.special.RequirementDto;
import net.sf.anathema.hero.magic.parser.dto.special.SpecialCharmDto;
import net.sf.anathema.hero.magic.parser.dto.special.TierDto;
import net.sf.anathema.hero.magic.parser.dto.special.TierRepurchaseDto;
import net.sf.anathema.hero.magic.parser.dto.special.TraitRepurchaseDto;
import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.hero.traits.model.types.ValuedTraitType;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("UnusedDeclaration")
public class RepurchaseCharmBuilder implements SpecialCharmBuilder {

  private final TraitTypeFinder traitTypeFinder = new TraitTypeFinder();

  @Override
  public ISpecialCharm readCharm(SpecialCharmDto overallDto) {
    RepurchaseDto repurchaseDto = overallDto.repurchase;
    if (repurchaseDto.isEssenceRepurchase) {
      return new EssenceFixedMultiLearnableCharm(new CharmName(overallDto.charmId));
    }
    CharmName charmName = new CharmName(overallDto.charmId);
    if (repurchaseDto.traitRepurchase != null) {
      return createTraitMultiLearnable(charmName, repurchaseDto.traitRepurchase);
    }
    if (repurchaseDto.staticRepurchase != null) {
      return new StaticMultiLearnableCharm(charmName, repurchaseDto.staticRepurchase.limit);
    }
    return createTierMultiLearnable(charmName, repurchaseDto);
  }

  private ISpecialCharm createTierMultiLearnable(CharmName id, RepurchaseDto repurchaseDto) {
    TierRepurchaseDto dto = repurchaseDto.tierRepurchase;
    List<CharmTier> tiers = new ArrayList<>();
    for (TierDto tierDto : dto.tiers) {
      tiers.add(createTier(tierDto));
    }
    return new TieredMultiLearnableCharm(id, tiers.toArray(new CharmTier[tiers.size()]));
  }

  private CharmTier createTier(TierDto dto) {
    TraitCharmTier traitCharmTier = new TraitCharmTier();
    for (RequirementDto requirement : dto.requirements) {
      TraitType traitType = traitTypeFinder.getTrait(requirement.traitType);
      traitCharmTier.addRequirement(new ValuedTraitType(traitType, requirement.traitValue));
    }
    return traitCharmTier;
  }

  private ISpecialCharm createTraitMultiLearnable(CharmName id, TraitRepurchaseDto dto) {
    TraitType trait = traitTypeFinder.getTrait(dto.limitingTrait);
    int modifier = dto.modifier;
    int absoluteMax = dto.absoluteMax;
    return new TraitDependentMultiLearnableCharm(id, absoluteMax, trait, modifier);
  }

  @Override
  public boolean supports(SpecialCharmDto overallDto) {
    return overallDto.repurchase != null;
  }
}