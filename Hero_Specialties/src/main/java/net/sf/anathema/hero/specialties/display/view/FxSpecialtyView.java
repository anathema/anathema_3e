package net.sf.anathema.hero.specialties.display.view;

import net.sf.anathema.library.fx.view.FxRemovableStringView;
import net.sf.anathema.library.resources.RelativePath;

public class FxSpecialtyView extends FxRemovableStringView implements SpecialtyView {

	public FxSpecialtyView(RelativePath removeIcon, String label) {
		super(removeIcon, label);
	}
	
	public void setEnabled(boolean enabled) {
		if (enabled) {
			button.enable();
		} else {
			button.disable();
		}
	}
}
