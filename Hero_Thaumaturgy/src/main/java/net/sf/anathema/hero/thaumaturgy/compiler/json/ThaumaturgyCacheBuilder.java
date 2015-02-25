package net.sf.anathema.hero.thaumaturgy.compiler.json;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.hero.thaumaturgy.compiler.ThaumaturgyRitualCacheImpl;
import net.sf.anathema.hero.thaumaturgy.compiler.json.template.RitualListTemplate;
import net.sf.anathema.hero.thaumaturgy.compiler.json.template.RitualTemplate;

public class ThaumaturgyCacheBuilder {
	private final List<RitualTemplate> ritualList = new ArrayList<>();
	
	public void addTemplate(RitualListTemplate template) {
		for (RitualTemplate merit : template.rituals) {
			ritualList.add(merit);
		}
	}

	public ThaumaturgyRitualCacheImpl createCache() {
		ThaumaturgyRitualCacheImpl meritCache = new ThaumaturgyRitualCacheImpl();
		ritualList.forEach(meritCache::addRitual);
		return meritCache;
	}
}
