package net.sf.anathema.hero.charms.display.tooltip;

import net.sf.anathema.magic.data.Magic;

public interface IMagicSourceStringBuilder<T extends Magic> {

  String createSourceString(T magic);

  String createShortSourceString(T charm);
}