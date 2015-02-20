package net.sf.anathema.equipment.character;

import net.sf.anathema.equipment.stats.IEquipmentStats;
import net.sf.anathema.equipment.stats.ItemStatsSet;
import net.sf.anathema.equipment.template.ItemCost;
import net.sf.anathema.library.event.ChangeListener;

public interface IEquipmentItem {

  String getTitle();

  String getDescription();

  String getTemplateId();

  String getBaseDescription();

  void setPersonalization(String title, String description);

  void setPersonalization(IEquipmentItem item);

  ItemCost getCost();

  ItemStatsSet getStats();

  IEquipmentStats getStat(String name);

  void setPrintEnabled(IEquipmentStats equipment, boolean enabled);

  boolean isPrintEnabled(IEquipmentStats stats);

  void setUnprinted();

  void setPrinted(String printedStatId);

  boolean isAttuned();

  void addChangeListener(ChangeListener listener);

  void removeChangeListener(ChangeListener listener);

  void setTitle(String title);

  void setDescription(String description);
}