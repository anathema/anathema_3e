package net.sf.anathema.hero.spiritual.display;

import net.sf.anathema.hero.framework.display.labelledvalue.IValueView;
import net.sf.anathema.library.view.IntValueView;

public interface SpiritualTraitsView {

  void initGui(SpiritualTraitsViewProperties properties);

  IntValueView addWillpower(String labelText, int maxValue);

  IntValueView addEssenceView(String labelText, int maxValue);

  IValueView<String> addPoolView(String labelText);
}