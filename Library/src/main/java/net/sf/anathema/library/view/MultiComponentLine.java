package net.sf.anathema.library.view;

import net.sf.anathema.library.model.IntegerModel;
import net.sf.anathema.library.text.ITextView;

public interface MultiComponentLine {

  ITextView addFieldsView(String labelText);

  IntegerView addIntegerView(String labelText, IntegerModel integerDescription);
}
