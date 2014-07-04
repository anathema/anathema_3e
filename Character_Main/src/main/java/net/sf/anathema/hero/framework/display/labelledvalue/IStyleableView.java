package net.sf.anathema.hero.framework.display.labelledvalue;

import net.sf.anathema.library.presenter.FontStyle;
import net.sf.anathema.library.presenter.RGBColor;

public interface IStyleableView {

  void setTextColor(RGBColor color);

  void setFontStyle(FontStyle style);
}