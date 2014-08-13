package net.sf.anathema.hero.merits.display;

import net.sf.anathema.library.fx.dot.ExtensibleDotView;
import net.sf.anathema.library.interaction.model.Command;

public interface MeritItemView extends ExtensibleDotView {
	void addButtonListener(Command command);
}
