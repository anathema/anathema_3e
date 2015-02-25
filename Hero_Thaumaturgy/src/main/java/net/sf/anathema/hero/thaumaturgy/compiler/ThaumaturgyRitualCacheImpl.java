package net.sf.anathema.hero.thaumaturgy.compiler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.anathema.hero.thaumaturgy.compiler.json.template.RitualTemplate;
import net.sf.anathema.hero.thaumaturgy.model.RitualImpl;
import net.sf.anathema.hero.thaumaturgy.model.RitualReference;
import net.sf.anathema.hero.thaumaturgy.model.ThaumaturgyRitual;

public class ThaumaturgyRitualCacheImpl implements ThaumaturgyRitualCache {
  private final Map<String, ThaumaturgyRitual> rituals = new HashMap<String, ThaumaturgyRitual>();

  public void addRitual(RitualTemplate option) {
    rituals.put(option.name, new RitualImpl(option));
  }

  @Override
  public List<ThaumaturgyRitual> getAllRitualOptions() {
    List<ThaumaturgyRitual> options = new ArrayList<>(rituals.values());
    options.sort((m1, m2) -> m1.getId().compareTo(m2.getId()));
    return options;
  }

	@Override
	public ThaumaturgyRitual getRitualByReference(RitualReference reference) {
		return rituals.get(reference.name);
	}
}