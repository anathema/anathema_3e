package net.sf.anathema.hero.languages.display.presenter;

import net.sf.anathema.library.interaction.model.Command;

public interface RemovableEntryView {

  void addButtonListener(Command command);

  void delete();
}