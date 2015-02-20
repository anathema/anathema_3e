package net.sf.anathema.equipment.database;

import net.sf.anathema.equipment.template.IEquipmentTemplate;

public interface IEquipmentTemplateProvider {

  String[] getAllAvailableTemplateIds();

  IEquipmentTemplate loadTemplate(String templateId);
}