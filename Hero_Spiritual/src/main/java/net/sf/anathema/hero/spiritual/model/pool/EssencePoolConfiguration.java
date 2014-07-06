package net.sf.anathema.hero.spiritual.model.pool;

import net.sf.anathema.hero.spiritual.template.EssencePoolTemplate;
import net.sf.anathema.hero.spiritual.template.PoolPartTemplate;
import net.sf.anathema.hero.traits.model.Trait;
import net.sf.anathema.hero.traits.model.TraitMap;

import java.util.ArrayList;
import java.util.List;

public class EssencePoolConfiguration {

  private final int personalBase;
  private final List<PoolPartTemplate> personalParts;
  private final int peripheralBase;
  private final List<PoolPartTemplate> peripheralParts;

  public EssencePoolConfiguration(EssencePoolTemplate template) {
    EssenceExpressionParser personalParser = new EssenceExpressionParser(template.personalPool);
    this.personalBase = personalParser.getBase();
    this.personalParts = personalParser.getPartTemplates();
    EssenceExpressionParser peripheralParser = new EssenceExpressionParser(template.peripheralPool);
    this.peripheralBase = peripheralParser.getBase();
    this.peripheralParts = peripheralParser.getPartTemplates();
  }

  public FactorizedTrait[] getPersonalTraits(TraitMap traitMap) {
    return createFactorizedTraits(personalParts, traitMap);
  }

  public FactorizedTrait[] getPeripheralTraits(TraitMap traitMap) {
    return createFactorizedTraits(peripheralParts, traitMap);
  }

  private FactorizedTrait[] createFactorizedTraits(List<PoolPartTemplate> parts, TraitMap traitMap) {
    if (parts.isEmpty()) {
      return new FactorizedTrait[0];
    }
    List<FactorizedTrait> traits = new ArrayList<>();
    for (PoolPartTemplate part : parts) {
      Trait trait = traitMap.getTrait(part.traitType);
      traits.add(new FactorizedTrait(trait, part.multiplier));
    }
    return traits.toArray(new FactorizedTrait[traits.size()]);
  }

  public int getPeripheralBase() {
    return peripheralBase;
  }

  public int getPersonalBase() {
    return personalBase;
  }
}