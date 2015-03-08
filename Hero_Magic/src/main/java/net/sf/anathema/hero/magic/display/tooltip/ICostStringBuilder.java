package net.sf.anathema.hero.magic.display.tooltip;

import net.sf.anathema.magic.data.cost.Cost;

public interface ICostStringBuilder<T extends Cost> {

  String getCostString(T cost);
}