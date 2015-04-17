package net.sf.anathema.hero.thaumaturgy.model;

import net.sf.anathema.hero.traits.model.TraitType;

import java.util.ArrayList;
import java.util.Collection;

public class NullRitualOption implements ThaumaturgyRitual {

	@Override
	public boolean isLegalValue(int value) {
		return false;
	}

	@Override
	public int getMinimumValue() {
		return 0;
	}

	@Override
	public int getMaximumValue() {
		return 0;
	}

  @Override
  public TraitType getTraitType() {
	return new TraitType("");
  }

  @Override
	public Collection<String> getSuggestions() {
		return new ArrayList<>();
	}
}