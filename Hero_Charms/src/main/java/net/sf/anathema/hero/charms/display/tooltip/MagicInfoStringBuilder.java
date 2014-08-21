package net.sf.anathema.hero.charms.display.tooltip;

import net.sf.anathema.charm.data.cost.Cost;
import net.sf.anathema.charm.data.cost.CostList;
import net.sf.anathema.charm.data.cost.HealthCost;
import net.sf.anathema.library.resources.Resources;
import net.sf.anathema.magic.data.Magic;

public class MagicInfoStringBuilder implements IMagicInfoStringBuilder {

  private final ICostStringBuilder<Cost> essenceBuilder;
  private final ICostStringBuilder<Cost> sorcerousMotesBuilder;
  private final ICostStringBuilder<Cost> willpowerBuilder;
  private final ICostStringBuilder<HealthCost> healthBuilder;
  private final ICostStringBuilder<Cost> experienceBuilder;
  private final MagicInfoStringConcatenator concatenator;

  public MagicInfoStringBuilder(Resources resources, ICostStringBuilder<Cost> essenceBuilder, ICostStringBuilder<Cost> sorcerousMotesBuilder, ICostStringBuilder<Cost> willpowerBuilder,
                                ICostStringBuilder<HealthCost> healthBuilder, ICostStringBuilder<Cost> experienceBuilder) {
    this.essenceBuilder = essenceBuilder;
    this.sorcerousMotesBuilder = sorcerousMotesBuilder;
    this.willpowerBuilder = willpowerBuilder;
    this.healthBuilder = healthBuilder;
    this.experienceBuilder = experienceBuilder;
    this.concatenator = new MagicInfoStringConcatenator(resources);
  }

  @Override
  public String createCostString(Magic magic) {
    CostList temporaryCost = magic.getTemporaryCost();
    String essenceCost = essenceBuilder.getCostString(temporaryCost.getEssenceCost());
    String sorcerousMoteCost = sorcerousMotesBuilder.getCostString(temporaryCost.getSorcerousMotesCost());
    String willpowerCost = willpowerBuilder.getCostString(temporaryCost.getWillpowerCost());
    String healthCost = healthBuilder.getCostString(temporaryCost.getHealthCost());
    String xpCost = experienceBuilder.getCostString(temporaryCost.getXPCost());
    return concatenator.buildCostString(essenceCost, sorcerousMoteCost, willpowerCost, healthCost, xpCost);
  }
}