package net.sf.anathema.hero.charms.display.tooltip;

import net.sf.anathema.charm.data.cost.Cost;

public interface ICostStringBuilder<T extends Cost> {

  String getCostString(T cost);
}