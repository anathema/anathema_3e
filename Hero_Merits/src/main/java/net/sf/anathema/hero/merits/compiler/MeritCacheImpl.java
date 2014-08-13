package net.sf.anathema.hero.merits.compiler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.anathema.hero.merits.compiler.json.template.MeritTemplate;
import net.sf.anathema.hero.merits.model.MeritCategory;
import net.sf.anathema.hero.merits.model.MeritOption;
import net.sf.anathema.hero.merits.model.MeritOptionImpl;

public class MeritCacheImpl implements MeritCache {
	private final Map<String, MeritOption> merits = new HashMap<String, MeritOption>();
	
	public void addMerit(MeritTemplate option) {
		merits.put(option.name, new MeritOptionImpl(option));
	}
	
	@Override
	public List<MeritOption> getAllMeritOptions() {
		List<MeritOption> options = new ArrayList<>(merits.values());
		options.sort((m1, m2) -> m1.getId().compareTo(m2.getId()));
		return options;
	}
	
	public MeritOption getMeritOptionByName(String name, boolean returnCustom) {
		MeritOption option = merits.get(name);
		if (option == null && returnCustom) {
			return getCustomMeritOption(name);
		}
		return option;
	}
	
	private MeritOption getCustomMeritOption(String name) {
		MeritTemplate template = new MeritTemplate();
		template.name = name;
		template.type = MeritCategory.Purchased.toString();
		template.repurchases = true;
		template.values = "1-5";
		template.requirements = new ArrayList<>();
		return new MeritOptionImpl(template);
	}
}
