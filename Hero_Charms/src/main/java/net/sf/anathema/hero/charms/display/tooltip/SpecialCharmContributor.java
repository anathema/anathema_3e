package net.sf.anathema.hero.charms.display.tooltip;

import net.sf.anathema.hero.charms.model.special.CharmSpecialLearning;
import net.sf.anathema.hero.charms.model.special.learning.multilearn.AbstractMultiLearnableCharm;
import net.sf.anathema.hero.charms.model.special.learning.multilearn.CharmTier;
import net.sf.anathema.hero.charms.model.special.learning.multilearn.EssenceFixedMultiLearnableCharm;
import net.sf.anathema.hero.charms.model.special.learning.multilearn.StaticMultiLearnableCharm;
import net.sf.anathema.hero.charms.model.special.learning.multilearn.TieredMultiLearnableCharm;
import net.sf.anathema.hero.charms.model.special.learning.multilearn.TraitDependentMultiLearnableCharm;
import net.sf.anathema.hero.magic.display.tooltip.MagicTooltipContributor;
import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.hero.traits.model.TraitTypeUtils;
import net.sf.anathema.library.resources.Resources;
import net.sf.anathema.library.tooltip.ConfigurableTooltip;
import net.sf.anathema.magic.data.Charm;
import net.sf.anathema.magic.data.Magic;

import static net.sf.anathema.hero.traits.model.types.CommonTraitTypes.Essence;

public class SpecialCharmContributor implements MagicTooltipContributor {
  private Resources resources;

  public SpecialCharmContributor(Resources resources) {
    this.resources = resources;
  }

  @Override
  public void buildStringForMagic(ConfigurableTooltip tooltip, Magic magic, Object specialDetails) {
    if (magic instanceof Charm && specialDetails instanceof CharmSpecialLearning) {
      Charm charm = (Charm) magic;
      CharmSpecialLearning details = (CharmSpecialLearning) specialDetails;
      if (details instanceof AbstractMultiLearnableCharm) {
        String label = resources.getString("CharmTreeView.ToolTip.Repurchases");
        String repurchaseInfo = null;
        if (details instanceof StaticMultiLearnableCharm) {
          repurchaseInfo = printStaticLimit((StaticMultiLearnableCharm) details);
        }
        if (details instanceof EssenceFixedMultiLearnableCharm) {
          return;
        }
        if (details instanceof TraitDependentMultiLearnableCharm) {
          repurchaseInfo = printTraitLimit((TraitDependentMultiLearnableCharm) details);
        }
        if (details instanceof TieredMultiLearnableCharm) {
          repurchaseInfo = printTieredLimit(charm, (TieredMultiLearnableCharm) details);
        }
        tooltip.appendLine(label, repurchaseInfo);
      }
    }
  }

  private String printTieredLimit(Charm charm, TieredMultiLearnableCharm details) {
    StringBuilder builder = new StringBuilder();
    CharmTier[] tiers = details.getTiers();
    CharmTier first = tiers[0], second = tiers[1], last = tiers[tiers.length - 1];
    for (CharmTier tier : tiers) {
      if (tier.equals(first)) {
        continue;
      }
      if (tier.equals(last) && !tier.equals(second)) {
        builder.append(resources.getString("CharmTreeView.ToolTip.Repurchases.And"));
        builder.append(ConfigurableTooltip.Space);
      }
      if (tier.equals(second) || tiers.length <= 3) {
        builder.append(resources.getString("Essence"));
        builder.append(ConfigurableTooltip.Space);
      }
      builder.append(tier.getRequirement(Essence));
      TraitType primaryTraitType =  new TraitTypeUtils().getPrimaryTraitType(charm);
      int traitRequirement = tier.getRequirement(primaryTraitType);
      if (traitRequirement > 0) {
        builder.append("/");
        if (tier.equals(second) || tiers.length <= 3) {
          builder.append(resources.getString(primaryTraitType.getId()));
          builder.append(ConfigurableTooltip.Space);
        }
        builder.append(traitRequirement);
      }
      if (!tier.equals(last)) {
        builder.append(ConfigurableTooltip.CommaSpace);
      }
    }
    return builder.toString();
  }

  private String printTraitLimit(TraitDependentMultiLearnableCharm details) {
    StringBuilder builder = new StringBuilder();
    builder.append("(");
    TraitType traitType = details.getTraitType();
    builder.append(resources.getString(traitType.getId()));
    if (details.getModifier() != 0) {
      builder.append(details.getModifier());
    }
    builder.append(")");
    builder.append(ConfigurableTooltip.Space);
    builder.append(resources.getString("CharmTreeView.ToolTip.Repurchases.Times"));
    return builder.toString();
  }

  private String printStaticLimit(StaticMultiLearnableCharm details) {
    return resources.getString("CharmTreeView.ToolTip.Repurchases.Static" + details.getAbsoluteLearnLimit());
  }
}