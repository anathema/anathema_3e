package net.sf.anathema.hero.equipment.sheet.content;

import net.sf.anathema.equipment.character.IEquipmentItem;
import net.sf.anathema.equipment.stats.IEquipmentStats;
import net.sf.anathema.hero.equipment.EquipmentModel;
import net.sf.anathema.hero.equipment.EquipmentModelFetcher;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.sheet.pdf.content.AbstractSubBoxContent;
import net.sf.anathema.hero.sheet.pdf.content.ListSubBoxContent;
import net.sf.anathema.library.resources.Resources;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PossessionsContent extends AbstractSubBoxContent implements ListSubBoxContent {

  private Hero hero;

  public PossessionsContent(Hero hero, Resources resources) {
    super(resources);
    this.hero = hero;
  }

  @Override
  public String getHeaderKey() {
    return "Possessions";
  }

  @Override
  public List<String> getPrintEntries() {
    List<String> printPossessions = new ArrayList<>();
    for (IEquipmentItem item : getEquipmentItems()) {
      if (isInArsenalOrPanopoly(item)) {
        continue;
      }
      String possession = item.getTitle();
      printPossessions.add(possession);
    }
    return printPossessions;
  }

  @Override
  public boolean useNewLineForEachEntry() {
    return false;
  }

  private boolean isInArsenalOrPanopoly(IEquipmentItem item) {
    for (IEquipmentStats stats : item.getStats()) {
      if (stats.representsItemForUseInCombat() && item.isPrintEnabled(stats)) {
        return true;
      }
    }
    return false;
  }

  private Collection<IEquipmentItem> getEquipmentItems() {
    EquipmentModel model = EquipmentModelFetcher.fetch(hero);
    return model.getEquipmentItems();
  }

}
