package net.sf.anathema.hero.equipment.display.presenter;

import java.util.ArrayList;
import java.util.Collection;

import net.sf.anathema.equipment.character.IEquipmentItem;
import net.sf.anathema.equipment.stats.IEquipmentStats;
import net.sf.anathema.equipment.stats.presentation.EquipmentStatsUIConfiguration;
import net.sf.anathema.hero.equipment.EquipmentHeroEvaluator;
import net.sf.anathema.library.resources.Resources;

public class FilteringEquipmentItemRenderer implements EquipmentItemRenderer {
  private final EquipmentStatsUIConfiguration configuration;
  private final EquipmentHeroEvaluator heroEvaluator;

  public FilteringEquipmentItemRenderer(Resources resources, EquipmentHeroEvaluator heroEvaluator) {
    this.heroEvaluator = heroEvaluator;
    this.configuration = new EquipmentStatsUIConfiguration(resources);
  }

  @Override
  public String getLabel(IEquipmentItem item) {
    return item.getTitle();
  }

  @Override
  public Collection<RelativePathWithDisabling> getIcons(IEquipmentItem item) {
    ArrayList<RelativePathWithDisabling> paths = new ArrayList<>();
    StatsPresentationFactory strategy = new StatsPresentationFactory(heroEvaluator, item);
    for (IEquipmentStats stats : item.getStats()) {
      if (!strategy.choosePresentationStrategy(stats).shouldStatsBeShown()) {
        continue;
      }
      RelativePathWithDisabling pathWithDisabling = new RelativePathWithDisabling();
      pathWithDisabling.path = configuration.getIconsRelativePath(stats);
      pathWithDisabling.enabled = item.isPrintEnabled(stats);
      paths.add(pathWithDisabling);
    }
    return paths;
  }
}