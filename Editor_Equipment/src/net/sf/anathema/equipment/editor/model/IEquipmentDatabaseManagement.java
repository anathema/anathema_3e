package net.sf.anathema.equipment.editor.model;

import net.sf.anathema.equipment.database.IEquipmentDatabase;
import net.sf.anathema.equipment.editor.stats.model.EquipmentStatsFactory;


public interface IEquipmentDatabaseManagement {

  IEquipmentTemplateEditModel getTemplateEditModel();

  IEquipmentDatabase getDatabase();

  EquipmentStatsFactory getStatsCreationFactory();
}