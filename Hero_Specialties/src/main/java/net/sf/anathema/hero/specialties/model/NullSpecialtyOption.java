package net.sf.anathema.hero.specialties.model;

import net.sf.anathema.hero.traits.model.TraitType;

import java.util.ArrayList;
import java.util.Collection;

public class NullSpecialtyOption implements SpecialtyType {

	@Override
	public Collection<String> getSuggestions() {
		return new ArrayList<>();
	}

	@Override
	public TraitType getTraitType() {
		return null;
	}
	
	@Override
	public String toString() {
	  return "";
	}


}