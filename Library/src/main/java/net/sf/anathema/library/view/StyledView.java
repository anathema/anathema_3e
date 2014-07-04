package net.sf.anathema.library.view;

import net.sf.anathema.library.presenter.FontStyle;
import net.sf.anathema.library.presenter.RGBColor;

public interface StyledView {

  void setTextColor(RGBColor color);

  void setFontStyle(FontStyle style);
}