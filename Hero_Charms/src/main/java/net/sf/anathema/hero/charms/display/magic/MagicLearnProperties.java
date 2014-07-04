package net.sf.anathema.hero.charms.display.magic;

import net.sf.anathema.library.presenter.AgnosticUIConfiguration;
import net.sf.anathema.library.resources.RelativePath;

import java.util.List;

public interface MagicLearnProperties {

  RelativePath getAddButtonIcon();

  String getAddButtonToolTip();

  RelativePath getRemoveButtonIcon();

  String getRemoveButtonToolTip();

  boolean isMagicSelectionAvailable(List selectedValue);

  AgnosticUIConfiguration getMagicRenderer();

  boolean isRemoveAllowed(List selectedObjects);
}