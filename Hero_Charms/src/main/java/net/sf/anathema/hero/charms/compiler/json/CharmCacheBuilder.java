package net.sf.anathema.hero.charms.compiler.json;

import net.sf.anathema.magic.template.CharmListTemplate;
import net.sf.anathema.hero.charms.compiler.CharmCacheImpl;

public interface CharmCacheBuilder {
	void addTemplate(CharmListTemplate charmList);

  public CharmCacheImpl createCache();
}
