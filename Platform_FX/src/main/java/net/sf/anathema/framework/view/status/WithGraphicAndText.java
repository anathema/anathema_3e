package net.sf.anathema.framework.view.status;

import net.sf.anathema.library.resources.RelativePath;

public interface WithGraphicAndText {
  void setText(String text);

  void setImage(RelativePath iconPath);
}
