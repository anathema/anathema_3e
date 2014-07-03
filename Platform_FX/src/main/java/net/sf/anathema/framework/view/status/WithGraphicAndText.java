package net.sf.anathema.framework.view.status;

import net.sf.anathema.lib.file.RelativePath;

public interface WithGraphicAndText {
  void setText(String text);

  void setImage(RelativePath iconPath);
}
