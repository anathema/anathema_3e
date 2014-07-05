package net.sf.anathema.hero.concept.model.concept;

import net.sf.anathema.library.event.ChangeListener;

public interface CasteSelection {

  CasteType getType();

  void setType(CasteType type);

  void addChangeListener(ChangeListener listener);

  boolean isNotSelected();
}