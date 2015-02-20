package net.sf.anathema.equipment.editor.model;

import java.util.List;

import net.sf.anathema.equipment.editor.model.description.IItemDescription;
import net.sf.anathema.equipment.stats.IEquipmentStats;
import net.sf.anathema.equipment.template.IEquipmentTemplate;
import net.sf.anathema.equipment.template.ItemCost;
import net.sf.anathema.library.event.ChangeListener;

public interface IEquipmentTemplateEditModel {

  void addStatistics(IEquipmentStats stats);

  void removeStatistics(IEquipmentStats... stats);

  IItemDescription getDescription();

  List<IEquipmentStats> getStats();

  boolean isDirty();

  void setEditTemplate(String templateId);

  void addStatsChangeListener(ChangeListener changeListener);

  IEquipmentTemplate createTemplate();

  String getEditTemplateId();

  void setNewTemplate();
  
  void copyNewTemplate( String salt );
  
  void addCostChangeListener(ChangeListener listener);
  
  void setCost(ItemCost cost);
  
  ItemCost getCost();

  void addMagicalMaterialChangeListener(ChangeListener listener);

  void addCompositionChangeListener(ChangeListener listener);

  void replaceStatistics(IEquipmentStats selectedStats, IEquipmentStats equipmentStats);
}