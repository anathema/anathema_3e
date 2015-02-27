package net.sf.anathema.library.view;

import net.sf.anathema.library.interaction.model.Command;

public interface OptionalEntryItemView {
	void addButtonListener(Command command);
	
	void setEnabled(boolean enable);
	
	void remove();
}
