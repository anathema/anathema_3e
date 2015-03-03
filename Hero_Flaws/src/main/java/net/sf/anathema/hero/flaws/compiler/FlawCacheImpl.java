package net.sf.anathema.hero.flaws.compiler;

import net.sf.anathema.hero.flaws.compiler.template.FlawTemplate;
import net.sf.anathema.hero.flaws.model.FlawOption;
import net.sf.anathema.hero.flaws.model.FlawOptionImpl;
import net.sf.anathema.library.model.OptionalEntryCategory;
import net.sf.anathema.library.model.OptionalEntryReference;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

public class FlawCacheImpl implements FlawCache {
  private final Map<String, FlawOption> Flaws = new HashMap<String, FlawOption>();

  public void addFlaw(FlawTemplate option) {
    Flaws.put(option.name, new FlawOptionImpl(option));
  }

  @Override
  public List<FlawOption> getAllOptions() {
    List<FlawOption> options = new ArrayList<>(Flaws.values());
    options.sort((m1, m2) -> m1.getId().compareTo(m2.getId()));
    return options;
  }

  public FlawOption getFlawOptionByName(OptionalEntryReference reference) {
    return Flaws.get(reference.name);
  }

	@Override
	public FlawOption getOptionByReference(OptionalEntryReference reference) {
		return Flaws.get(reference.name);
	}

  @Override
  public List<FlawOption> getAllOptionsForCategory(
      OptionalEntryCategory category) {
    return new ArrayList<>();
  }
}