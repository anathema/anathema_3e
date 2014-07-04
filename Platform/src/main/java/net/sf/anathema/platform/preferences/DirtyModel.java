package net.sf.anathema.platform.preferences;

import net.sf.anathema.library.interaction.model.Command;

public interface DirtyModel {
  void whenDirtied(Command command);
}
