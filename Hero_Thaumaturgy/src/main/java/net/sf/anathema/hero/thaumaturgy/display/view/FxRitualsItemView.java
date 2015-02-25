package net.sf.anathema.hero.thaumaturgy.display.view;

import net.sf.anathema.hero.thaumaturgy.display.presenter.RitualItemView;
import net.sf.anathema.library.fx.dot.FxDotView;
import net.sf.anathema.library.fx.dot.FxExtensibleDotView;
import net.sf.anathema.library.interaction.model.Command;

public class FxRitualsItemView extends FxExtensibleDotView implements RitualItemView {
	
	public FxRitualsItemView(FxDotView dotView) {
		super(dotView);
		//this.label = label;
		//this.buttonIcon = buttonIcon;
	}

	@Override
	public void addButtonListener(Command command) {
		// TODO Auto-generated method stub
		
	}
}
