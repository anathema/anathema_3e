package net.sf.anathema.framework.view.perspective;

import net.sf.anathema.library.resources.RelativePath;

public interface PerspectiveToggle {

  void setIcon(RelativePath relativePath);

  void setTooltip(String key);

  void setText(String key);
}
