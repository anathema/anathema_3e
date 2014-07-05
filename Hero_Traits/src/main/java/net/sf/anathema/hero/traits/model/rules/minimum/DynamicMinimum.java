package net.sf.anathema.hero.traits.model.rules.minimum;

import net.sf.anathema.library.event.ChangeListener;

public interface DynamicMinimum {

  int getMinimum();

  void addChangedListener(ChangeListener listener);
}
