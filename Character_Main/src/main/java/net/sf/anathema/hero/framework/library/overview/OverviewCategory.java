package net.sf.anathema.hero.framework.library.overview;

import net.sf.anathema.hero.framework.display.labelledvalue.LabelledAllotmentView;
import net.sf.anathema.library.view.StyledValueView;

public interface OverviewCategory {

  LabelledAllotmentView addAlotmentView(String labelText, int maxValueLength);

  StyledValueView<Integer> addIntegerValueView(String labelText, int maxValueLength);

  StyledValueView<String> addStringValueView(String labelText);
}