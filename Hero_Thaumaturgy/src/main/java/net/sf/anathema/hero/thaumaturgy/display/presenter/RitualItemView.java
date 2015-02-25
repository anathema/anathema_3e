package net.sf.anathema.hero.thaumaturgy.display.presenter;

import net.sf.anathema.library.fx.dot.ExtensibleDotView;
import net.sf.anathema.library.interaction.model.Command;

public interface RitualItemView extends ExtensibleDotView {
	void addButtonListener(Command command);
}
