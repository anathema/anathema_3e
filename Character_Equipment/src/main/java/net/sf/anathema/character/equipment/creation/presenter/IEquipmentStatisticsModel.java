package net.sf.anathema.character.equipment.creation.presenter;

import net.sf.anathema.lib.workflow.textualdescription.ITextualDescription;
import net.sf.anathema.library.event.ChangeListener;

public interface IEquipmentStatisticsModel {

  ITextualDescription getName();

  boolean isValid();
  
  void addValidListener(ChangeListener listener);
}