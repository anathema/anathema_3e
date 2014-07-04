package net.sf.anathema.framework.preferences.elements;

import net.sf.anathema.library.interaction.model.Command;

public interface DirtyModel {
  void whenDirtied(Command command);
}
