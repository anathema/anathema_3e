package net.sf.anathema.hero.merits.compiler.json;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.hero.merits.compiler.MeritCacheImpl;
import net.sf.anathema.hero.merits.compiler.json.template.MeritListTemplate;
import net.sf.anathema.hero.merits.compiler.json.template.MeritTemplate;

public class MeritCacheBuilder {
	private final List<MeritTemplate> meritList = new ArrayList<>();
	private final List<MeritListTemplate> templateList = new ArrayList<>();
	
	public void addTemplate(MeritListTemplate template) {
		templateList.add(template);
		for (MeritTemplate merit : template.merits) {
			meritList.add(merit);
		}
	}

	public MeritCacheImpl createCache() {
		MeritCacheImpl meritCache = new MeritCacheImpl();
		meritList.forEach(meritCache::addMerit);
		return meritCache;
	}
}
