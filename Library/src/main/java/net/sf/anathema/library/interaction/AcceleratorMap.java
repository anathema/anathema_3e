package net.sf.anathema.library.interaction;

import net.sf.anathema.library.interaction.model.Command;
import net.sf.anathema.library.interaction.model.Hotkey;

public interface AcceleratorMap {

  void register(Hotkey hotkey, Command command);
}