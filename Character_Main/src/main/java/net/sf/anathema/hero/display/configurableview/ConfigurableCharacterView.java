package net.sf.anathema.hero.display.configurableview;

import net.sf.anathema.library.interaction.model.Tool;
import net.sf.anathema.library.text.ITextView;
import net.sf.anathema.library.view.IntValueView;

public interface ConfigurableCharacterView {

  ITextView addLineView(String labelText);

  ITextView addAreaView(String labelText);

  Tool addEditAction();

  MultiComponentLine addMultiComponentLine();

  IntValueView addDotSelector(String label, int maxValue);
}