package net.sf.anathema.library.view.fx;

import net.sf.anathema.library.fx.dot.FxDotView;
import net.sf.anathema.library.fx.dot.FxExtensibleDotView;
import net.sf.anathema.library.interaction.model.Command;
import net.sf.anathema.library.view.OptionalTraitItemView;

public class FxOptionalTraitItemView extends FxExtensibleDotView implements OptionalTraitItemView {
	
	public FxOptionalTraitItemView(FxDotView dotView) {
		super(dotView);
		//this.label = label;
		//this.buttonIcon = buttonIcon;
	}

	@Override
	public void addButtonListener(Command command) {
		// TODO Auto-generated method stub
		
	}
}
