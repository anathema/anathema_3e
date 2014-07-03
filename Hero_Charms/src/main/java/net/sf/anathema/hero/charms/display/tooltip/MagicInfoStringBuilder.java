package net.sf.anathema.hero.charms.display.tooltip;

import net.sf.anathema.charm.data.cost.CostList;
import net.sf.anathema.charm.data.cost.HealthCost;
import net.sf.anathema.magic.Magic;
import net.sf.anathema.charm.data.cost.Cost;
import net.sf.anathema.framework.environment.Resources;

public class MagicInfoStringBuilder implements IMagicInfoStringBuilder {

  private final ICostStringBuilder<Cost> essenceBuilder;
  private final ICostStringBuilder<Cost> willpowerBuilder;
  private final ICostStringBuilder<HealthCost> healthBuilder;
  private final ICostStringBuilder<Cost> experienceBuilder;
  private final MagicInfoStringConcatenator concatenator;

  public MagicInfoStringBuilder(Resources resources, ICostStringBuilder<Cost> essenceBuilder, ICostStringBuilder<Cost> willpowerBuilder,
                                ICostStringBuilder<HealthCost> healthBuilder, ICostStringBuilder<Cost> experienceBuilder) {
    this.essenceBuilder = essenceBuilder;
    this.willpowerBuilder = willpowerBuilder;
    this.healthBuilder = healthBuilder;
    this.experienceBuilder = experienceBuilder;
    this.concatenator = new MagicInfoStringConcatenator(resources);
  }

  @Override
  public String createCostString(Magic magic) {
    CostList temporaryCost = magic.getTemporaryCost();
    String essenceCost = essenceBuilder.getCostString(temporaryCost.getEssenceCost());
    String willpowerCost = willpowerBuilder.getCostString(temporaryCost.getWillpowerCost());
    String healthCost = healthBuilder.getCostString(temporaryCost.getHealthCost());
    String xpCost = experienceBuilder.getCostString(temporaryCost.getXPCost());
    return concatenator.buildCostString(essenceCost, willpowerCost, healthCost, xpCost);
  }
}