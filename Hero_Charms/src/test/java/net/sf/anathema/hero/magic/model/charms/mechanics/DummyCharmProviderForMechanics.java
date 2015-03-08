package net.sf.anathema.hero.magic.model.charms.mechanics;

import net.sf.anathema.magic.data.Charm;
import net.sf.anathema.magic.data.reference.CategoryReference;
import net.sf.anathema.magic.data.reference.CharmName;
import net.sf.anathema.hero.charms.compiler.CharmCache;
import net.sf.anathema.hero.charms.model.special.CharmSpecialLearning;
import net.sf.anathema.hero.charms.model.special.CharmSpecialMechanic;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DummyCharmProviderForMechanics implements CharmCache {

	private Map<CharmName, Charm> charmSet = new HashMap<>();
	private Multimap<CharmName, CharmSpecialMechanic> mechanics = ArrayListMultimap.create();
	
	public void addCharm(Charm charm, CharmSpecialMechanic mechanic) {
		charmSet.put(charm.getName(), charm);
		mechanics.put(charm.getName(),  mechanic);
	}
	
	@Override
	public List<CategoryReference> getAllCategories() {
		List<CategoryReference> list = new ArrayList<CategoryReference>();
		list.add(new CategoryReference("AnyCategory"));
		return list;
	}

	@Override
	public List<Charm> getCharms(CategoryReference category) {
		List<Charm> charms = new ArrayList<>();
		charms.addAll(charmSet.values());
		return charms;
	}

	@Override
	public List<CharmSpecialLearning> getSpecialLearningCharms(
			CategoryReference category) {
		return new ArrayList<CharmSpecialLearning>();
	}

	@Override
	public Collection<Charm> getCharmsWithSpecialMechanics() {
		return mechanics.keySet().stream().map(key -> charmSet.get(key)).collect(Collectors.toList());
	}

	@Override
	public Collection<CharmSpecialMechanic> getSpecialMechanicsForCharm(
			CharmName name) {
		return mechanics.get(name);
	}

	@Override
	public Charm getCharmById(CharmName charmId) {
		return null;
	}

}
