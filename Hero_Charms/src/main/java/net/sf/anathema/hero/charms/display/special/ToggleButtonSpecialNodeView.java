package net.sf.anathema.hero.charms.display.special;

import net.sf.anathema.library.view.BooleanView;
import net.sf.anathema.platform.tree.display.SpecialNodeView;

public interface ToggleButtonSpecialNodeView extends SpecialNodeView {
  BooleanView addSubeffect(String label);
}
