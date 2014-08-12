package net.sf.anathema.hero.merits.display;

import net.sf.anathema.library.fx.dot.FxDotView;
import net.sf.anathema.library.fx.dot.FxExtensibleDotView;
import net.sf.anathema.library.interaction.model.Command;

public class FxMeritItemView extends FxExtensibleDotView implements MeritItemView {
	
	public FxMeritItemView(FxDotView dotView) {
		super(dotView);
		//this.label = label;
		//this.buttonIcon = buttonIcon;
	}

	@Override
	public void addButtonListener(Command command) {
		// TODO Auto-generated method stub
		
	}
}
