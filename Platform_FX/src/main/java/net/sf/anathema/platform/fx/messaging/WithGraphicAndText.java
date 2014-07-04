package net.sf.anathema.platform.fx.messaging;

import net.sf.anathema.library.resources.RelativePath;

public interface WithGraphicAndText {
  void setText(String text);

  void setImage(RelativePath iconPath);
}
