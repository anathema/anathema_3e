package net.sf.anathema.platform.fx.perspective;

import net.sf.anathema.library.interaction.model.Tool;
import net.sf.anathema.library.resources.RelativePath;
import net.sf.anathema.library.resources.Resources;
import net.sf.anathema.platform.utility.UtilityToggle;

public class UtilityPerspectiveToggle implements UtilityToggle {

  private Tool action;
  private Resources resources;

  public UtilityPerspectiveToggle(Tool action, Resources resources) {
    this.action = action;
    this.resources = resources;
  }

  @Override
  public void setIcon(RelativePath relativePath) {
    action.setIcon(relativePath);
  }

  @Override
  public void setTooltip(String key) {
    action.setTooltip(resources.getString(key));
  }

  @Override
  public void setText(String key) {
    action.setText(resources.getString(key));
  }
}