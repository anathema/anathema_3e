package net.sf.anathema.hero.charms.display.tooltip;

import net.sf.anathema.magic.Magic;

public interface IMagicInfoStringBuilder {

  String createCostString(Magic magic);
}