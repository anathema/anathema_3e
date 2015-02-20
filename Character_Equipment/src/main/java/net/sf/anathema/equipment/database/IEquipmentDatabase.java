package net.sf.anathema.equipment.database;

import net.sf.anathema.equipment.template.IEquipmentTemplate;
import net.sf.anathema.library.event.ChangeListener;

public interface IEquipmentDatabase extends IEquipmentTemplateProvider {

  void addAvailableTemplateChangeListener(ChangeListener listener);

  void deleteTemplate(String editTemplateId);

  void saveTemplate(IEquipmentTemplate template);

  void updateTemplate(String editTemplateId, IEquipmentTemplate saveTemplate);
}