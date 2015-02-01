package net.sf.anathema.platform.utility;

import net.sf.anathema.library.resources.RelativePath;

public interface UtilityToggle {

  void setIcon(RelativePath relativePath);

  void setTooltip(String key);

  void setText(String key);
}
