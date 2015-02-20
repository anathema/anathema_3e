package net.sf.anathema.hero.equipment.persister;

import net.sf.anathema.equipment.character.IEquipmentItem;
import net.sf.anathema.equipment.stats.IEquipmentStats;
import net.sf.anathema.hero.equipment.EquipmentModel;
import net.sf.anathema.hero.equipment.EquipmentOptionsProvider;
import net.sf.anathema.hero.equipment.IEquipmentStatsOption;
import net.sf.anathema.hero.equipment.StatsOptions;

public class ItemToPtoTransformer {
  private EquipmentModel heroModel;

  public ItemToPtoTransformer(EquipmentModel heroModel) {
    this.heroModel = heroModel;
  }

  public EquipmentPto createPto(IEquipmentItem item) {
    EquipmentPto pto = new EquipmentPto();
    pto.templateId = item.getTemplateId();
    pto.customTitle = item.getTitle();
    if (!item.getBaseDescription().equals(item.getDescription())) {
      pto.description = item.getDescription();
    }
    for (IEquipmentStats stats : item.getStats()) {
      if (item.isPrintEnabled(stats)) {
        EquipmentStatsPto printPto = createStatsPto(item, stats);
        pto.printStats.add(printPto);
      }
    }
    return pto;
  }

  private EquipmentStatsPto createStatsPto(IEquipmentItem item, IEquipmentStats stats) {
    EquipmentStatsPto statsPto = new EquipmentStatsPto();
    statsPto.id = stats.getId();
    IEquipmentStats stat = item.getStat(stats.getId());
    EquipmentOptionsProvider optionProvider = heroModel.getOptionProvider();
    StatsOptions enabledStatOptions = optionProvider.getEnabledStatOptions(item, stat);
    for (IEquipmentStatsOption option : enabledStatOptions) {
      EquipmentOptionPto optionPto = new EquipmentOptionPto();
      optionPto.name = option.getName();
      optionPto.type = option.getType();
    }
    return statsPto;
  }
}