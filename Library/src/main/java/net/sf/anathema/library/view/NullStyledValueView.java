package net.sf.anathema.library.view;

import net.sf.anathema.library.presenter.FontStyle;
import net.sf.anathema.library.presenter.RGBColor;

public class NullStyledValueView<E> implements StyledValueView<E> {
  @Override
  public void setValue(E value) {
    //nothing to do
  }

  @Override
  public void setTextColor(RGBColor color) {
    //nothing to do
  }

  @Override
  public void setFontStyle(FontStyle style) {
    //nothing to do
  }
}