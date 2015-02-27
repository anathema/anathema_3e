package net.sf.anathema.library.view;

import net.sf.anathema.library.fx.dot.ExtensibleDotView;
import net.sf.anathema.library.interaction.model.Command;

public interface OptionalTraitItemView extends ExtensibleDotView {
	void addButtonListener(Command command);
	
	void setEnabled(boolean enable);
}
