package net.sf.anathema.hero.charms.display.magic;

import net.sf.anathema.library.resources.RelativePath;
import net.sf.anathema.library.resources.Resources;
import net.sf.anathema.platform.taskbar.BasicUi;

public abstract class AbstractMagicLearnProperties implements MagicLearnProperties {

  private final Resources resources;

  public AbstractMagicLearnProperties(Resources resources) {
    this.resources = resources;
  }

  @Override
  public RelativePath getAddButtonIcon() {
    return new BasicUi().getRightArrowIconPath();
  }

  @Override
  public RelativePath getRemoveButtonIcon() {
    return new BasicUi().getLeftArrowIconPath();
  }

  protected final Resources getResources() {
    return resources;
  }
}