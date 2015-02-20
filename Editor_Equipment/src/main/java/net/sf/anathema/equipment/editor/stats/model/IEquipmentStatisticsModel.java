package net.sf.anathema.equipment.editor.stats.model;

import net.sf.anathema.library.event.ChangeListener;
import net.sf.anathema.library.text.ITextualDescription;

public interface IEquipmentStatisticsModel {

  ITextualDescription getName();

  boolean isValid();
  
  void addValidListener(ChangeListener listener);
}