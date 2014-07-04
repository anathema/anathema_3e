package net.sf.anathema.hero.spiritual.display;

import net.sf.anathema.library.view.IntValueView;
import net.sf.anathema.library.view.StyledValueView;

public interface SpiritualTraitsView {

  void initGui(SpiritualTraitsViewProperties properties);

  IntValueView addWillpower(String labelText, int maxValue);

  IntValueView addEssenceView(String labelText, int maxValue);

  StyledValueView<String> addPoolView(String labelText);
}