package net.sf.anathema.equipment.editor.model;

import net.sf.anathema.equipment.database.IEquipmentDatabase;


public interface IEquipmentDatabaseManagement {

  IEquipmentTemplateEditModel getTemplateEditModel();

  IEquipmentDatabase getDatabase();

  EquipmentStatsFactory getStatsCreationFactory();
}