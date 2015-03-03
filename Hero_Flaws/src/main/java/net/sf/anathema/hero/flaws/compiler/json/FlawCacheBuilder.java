package net.sf.anathema.hero.flaws.compiler.json;

import net.sf.anathema.hero.flaws.compiler.FlawCacheImpl;
import net.sf.anathema.hero.flaws.compiler.template.FlawListTemplate;
import net.sf.anathema.hero.flaws.compiler.template.FlawTemplate;

import java.util.ArrayList;
import java.util.List;

public class FlawCacheBuilder {
	private final List<FlawTemplate> flawList = new ArrayList<>();
	
	public void addTemplate(FlawListTemplate template) {
		for (FlawTemplate flaw : template.flaws) {
			flawList.add(flaw);
		}
	}

	public FlawCacheImpl createCache() {
		FlawCacheImpl flawCache = new FlawCacheImpl();
		flawList.forEach(flawCache::addFlaw);
		return flawCache;
	}
}
