package net.sf.anathema.hero.charms.display.tooltip;

import net.sf.anathema.hero.magic.display.tooltip.MagicTooltipContributor;
import net.sf.anathema.hero.magic.display.tooltip.ScreenDisplayInfoContributor;
import net.sf.anathema.magic.data.Charm;
import net.sf.anathema.hero.magic.display.tooltip.source.MagicSourceContributor;
import net.sf.anathema.hero.charms.model.special.CharmSpecialLearning;
import net.sf.anathema.library.resources.Resources;
import net.sf.anathema.library.tooltip.ConfigurableTooltip;
import net.sf.anathema.magic.description.model.MagicDescriptionProvider;

import com.google.common.base.Preconditions;

import java.util.ArrayList;
import java.util.List;

public class CharmTooltipBuilderImpl implements CharmTooltipBuilder {
  private final List<MagicTooltipContributor> builders = new ArrayList<>();

  public CharmTooltipBuilderImpl(Resources resources, MagicDescriptionProvider magicDescriptionProvider) {
    builders.add(new MagicNameContributor(resources));
    builders.add(new ScreenDisplayInfoContributor(resources));
    builders.add(new CharmDurationContributor(resources));
    builders.add(new CharmTypeContributor(resources));
    builders.add(new CharmKeywordsContributor(resources));
    builders.add(new CharmPrerequisitesContributor(resources));
    builders.add(new SpecialCharmContributor(resources));
    builders.add(new MagicSourceContributor<Charm>(resources));
    builders.add(new MagicDescriptionContributor(magicDescriptionProvider));
  }

  @Override
  public void configureTooltipWithoutSpecials(Charm charm, ConfigurableTooltip tooltip) {
    configureTooltipWithSpecials(charm, null, tooltip);
  }

  @Override
  public final void configureTooltipWithSpecials(Charm charm, CharmSpecialLearning specialDetails, ConfigurableTooltip tooltip) {
    Preconditions.checkNotNull(charm);
    for (MagicTooltipContributor lineBuilder : builders) {
      lineBuilder.buildStringForMagic(tooltip, charm, specialDetails);
    }
  }
}