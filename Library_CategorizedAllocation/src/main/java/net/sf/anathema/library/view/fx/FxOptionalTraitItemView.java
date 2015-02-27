package net.sf.anathema.library.view.fx;

import net.sf.anathema.library.fx.dot.FxDotView;
import net.sf.anathema.library.fx.dot.FxExtensibleDotView;
import net.sf.anathema.library.interaction.model.Command;
import net.sf.anathema.library.interaction.model.Tool;
import net.sf.anathema.library.resources.RelativePath;
import net.sf.anathema.library.view.OptionalTraitItemView;

public class FxOptionalTraitItemView extends FxExtensibleDotView implements OptionalTraitItemView {
	
	private final Tool button;
	
	public FxOptionalTraitItemView(FxDotView dotView, RelativePath buttonIcon) {
		super(dotView);
		this.button = addToolBehind();
		this.button.setIcon(buttonIcon);
	}

	@Override
	public void addButtonListener(Command command) {
		button.setCommand(command);
	}

	@Override
	public void setEnabled(boolean enable) {
		if (enable) {
			button.enable();
		} else {
			button.disable();
		}
	}
}
