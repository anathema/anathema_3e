package net.sf.anathema.hero.magic.display.tooltip;

import net.sf.anathema.magic.data.Magic;

public interface IMagicInfoStringBuilder {

  String createCostString(Magic magic);
}