package net.sf.anathema.library.view.fx;

import net.sf.anathema.library.fx.view.FxRemovableStringView;
import net.sf.anathema.library.resources.RelativePath;
import net.sf.anathema.library.view.property.OptionalPropertyItemView;

public class FxOptionalPropertyItemView extends FxRemovableStringView implements OptionalPropertyItemView {

  public FxOptionalPropertyItemView(RelativePath removeIcon, String label) {
    super(removeIcon, label);
  }
  
  public void setEnabled(boolean enabled) {
    if (enabled) {
      button.enable();
    } else {
      button.disable();
    }
  }

  @Override
  public void remove() {
    this.delete();
  }
}
