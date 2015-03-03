package net.sf.anathema.hero.thaumaturgy;

import net.sf.anathema.hero.thaumaturgy.compiler.ThaumaturgyRitualCache;
import net.sf.anathema.hero.thaumaturgy.compiler.json.template.RitualTemplate;
import net.sf.anathema.hero.thaumaturgy.model.RitualImpl;
import net.sf.anathema.hero.thaumaturgy.model.ThaumaturgyRitual;
import net.sf.anathema.library.model.OptionalEntryCategory;
import net.sf.anathema.library.model.OptionalEntryReference;

import java.util.ArrayList;
import java.util.List;

public class DummyRitualProvider implements ThaumaturgyRitualCache {
	
	private final List<ThaumaturgyRitual> rituals = new ArrayList<>();
	
	public DummyRitualProvider() {
		rituals.add(new RitualImpl(new RitualTemplate("Second Bread", true, false)));
		rituals.add(new RitualImpl(new RitualTemplate("Read The Tea Leaves", true, true)));
		rituals.add(new RitualImpl(new RitualTemplate("Speak To Ozashun", false, true)));
	}

	@Override
	public List<ThaumaturgyRitual> getAllOptions() {
		return rituals;
	}

	@Override
	public List<ThaumaturgyRitual> getAllOptionsForCategory(OptionalEntryCategory category) {
		return null;
	}

	@Override
	public ThaumaturgyRitual getOptionByReference(OptionalEntryReference reference) {
		return rituals.stream().filter(ritual -> ritual.getId().toString().equals(reference.name)).findFirst().get();
	}

}
