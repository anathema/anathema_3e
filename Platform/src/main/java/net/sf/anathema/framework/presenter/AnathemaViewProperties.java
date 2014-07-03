package net.sf.anathema.framework.presenter;

import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.lib.file.RelativePath;

public class AnathemaViewProperties {

  private final Resources resources;

  public AnathemaViewProperties(Resources resources) {
    this.resources = resources;
  }

  public String getDefaultFrameTitle() {
    return resources.getString("MainFrame.Title");
  }

  public RelativePath getFrameIcon() {
    return new RelativePath("icons/AnathemaIcon.png");
  }

  public String getMainMenuName() {
    return resources.getString("AnathemaCore.MainMenu.Name");
  }

  public String getHelpMenuName() {
    return resources.getString("AnathemaCore.HelpMenu.Name");
  }
}