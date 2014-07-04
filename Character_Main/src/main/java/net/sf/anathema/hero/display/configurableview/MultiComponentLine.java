package net.sf.anathema.hero.display.configurableview;

import net.sf.anathema.hero.framework.IIntegerDescription;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;
import net.sf.anathema.library.view.IntegerView;

public interface MultiComponentLine {

  ITextView addFieldsView(String labelText);

  IntegerView addIntegerView(String labelText, IIntegerDescription integerDescription);
}
