package net.sf.anathema.hero.framework.display.labelledvalue;

import net.sf.anathema.library.view.StyledValueView;

public interface LabelledAllotmentView extends StyledValueView<Integer> {

  void setAllotment(int value);
}