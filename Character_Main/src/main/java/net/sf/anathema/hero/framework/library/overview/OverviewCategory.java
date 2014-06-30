package net.sf.anathema.hero.framework.library.overview;

import net.sf.anathema.hero.framework.display.labelledvalue.IValueView;
import net.sf.anathema.hero.framework.display.labelledvalue.LabelledAllotmentView;

public interface OverviewCategory {

  LabelledAllotmentView addAlotmentView(String labelText, int maxValueLength);

  IValueView<Integer> addIntegerValueView(String labelText, int maxValueLength);

  IValueView<String> addStringValueView(String labelText);
}