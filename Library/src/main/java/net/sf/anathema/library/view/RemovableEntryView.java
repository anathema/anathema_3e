package net.sf.anathema.library.view;

import net.sf.anathema.library.interaction.model.Command;

public interface RemovableEntryView {

  void addButtonListener(Command command);

  void delete();
}