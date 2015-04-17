package net.sf.anathema.hero.merits.compiler;

import net.sf.anathema.hero.merits.compiler.json.template.MeritTemplate;
import net.sf.anathema.hero.merits.model.MeritCategory;
import net.sf.anathema.hero.merits.model.MeritOption;
import net.sf.anathema.hero.merits.model.MeritOptionImpl;
import net.sf.anathema.library.model.OptionalEntryCategory;
import net.sf.anathema.library.model.OptionalEntryReference;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

public class MeritCacheImpl implements MeritCache {
  private final Map<String, MeritOption> merits = new HashMap<>();

  public void addMerit(MeritTemplate option) {
    merits.put(option.name, new MeritOptionImpl(option));
  }

  @Override
  public List<MeritOption> getAllOptions() {
    List<MeritOption> options = new ArrayList<>(merits.values());
    options.sort((m1, m2) -> m1.getTraitType().getId().compareTo(m2.getTraitType().getId()));
    return options;
  }

  public MeritOption getMeritOptionByName(OptionalEntryReference reference) {
    return merits.get(reference.name);
  }

	@Override
	public List<MeritOption> getAllOptionsForCategory(OptionalEntryCategory category) {
		return getAllOptions().stream().filter(option -> option.getCategory().equals(category)).collect(toList());
	}

	@Override
	public MeritOption getOptionByReference(OptionalEntryReference reference) {
		return merits.get(reference.name);
	}

  @Override
  public List<OptionalEntryCategory> getAllCategories() {
    return Arrays.asList(MeritCategory.values());
  }

  @Override
  public List<OptionalEntryCategory> getCurrentCategories() {
    return getAllCategories().stream().filter(category -> !getAllOptionsForCategory(category).isEmpty()).collect(toList());
  }
}