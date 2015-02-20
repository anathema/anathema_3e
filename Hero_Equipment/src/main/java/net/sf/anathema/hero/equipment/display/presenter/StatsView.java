package net.sf.anathema.hero.equipment.display.presenter;

import net.sf.anathema.library.event.ChangeListener;

public interface StatsView {
  void setSelected(boolean selected);

  boolean getSelected();

  void disable();

  void addChangeListener(ChangeListener changeListener);

  StatsView addOptionFlag(String label);

}
