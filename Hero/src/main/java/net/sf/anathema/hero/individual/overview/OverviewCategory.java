package net.sf.anathema.hero.individual.overview;

import net.sf.anathema.library.view.LabelledAllotmentView;
import net.sf.anathema.library.view.StyledValueView;

public interface OverviewCategory {

  LabelledAllotmentView addAllotmentView(String labelText, int maxValueLength);

  StyledValueView<Integer> addIntegerValueView(String labelText, int maxValueLength);

  StyledValueView<String> addStringValueView(String labelText);
}