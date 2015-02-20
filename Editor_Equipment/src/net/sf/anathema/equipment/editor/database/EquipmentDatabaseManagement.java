package net.sf.anathema.equipment.editor.database;

import net.sf.anathema.equipment.database.IEquipmentDatabase;
import net.sf.anathema.equipment.editor.model.EquipmentTemplateEditModel;
import net.sf.anathema.equipment.editor.model.IEquipmentDatabaseManagement;
import net.sf.anathema.equipment.editor.model.IEquipmentTemplateEditModel;
import net.sf.anathema.equipment.editor.stats.model.EquipmentStatsFactory;
import net.sf.anathema.equipment.editor.stats.model.impl.SimpleEquipmentStatsFactory;

public class EquipmentDatabaseManagement implements IEquipmentDatabaseManagement {

  private final EquipmentTemplateEditModel templateEditModel;
  private final IEquipmentDatabase database;
  private final EquipmentStatsFactory statsFactory = new SimpleEquipmentStatsFactory();

  public EquipmentDatabaseManagement(IEquipmentDatabase database) {
    this.database = database;
    this.templateEditModel = new EquipmentTemplateEditModel(database);
  }

  @Override
  public IEquipmentTemplateEditModel getTemplateEditModel() {
    return templateEditModel;
  }

  @Override
  public IEquipmentDatabase getDatabase() {
    return database;
  }

  @Override
  public EquipmentStatsFactory getStatsCreationFactory() {
    return statsFactory;
  }

}