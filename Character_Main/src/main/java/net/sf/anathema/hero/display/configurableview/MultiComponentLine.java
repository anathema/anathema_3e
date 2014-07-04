package net.sf.anathema.hero.display.configurableview;

import net.sf.anathema.library.model.IntegerModel;
import net.sf.anathema.library.text.ITextView;
import net.sf.anathema.library.view.IntegerView;

public interface MultiComponentLine {

  ITextView addFieldsView(String labelText);

  IntegerView addIntegerView(String labelText, IntegerModel integerDescription);
}
