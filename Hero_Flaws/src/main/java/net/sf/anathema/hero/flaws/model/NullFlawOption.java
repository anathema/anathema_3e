package net.sf.anathema.hero.flaws.model;

import java.util.ArrayList;
import java.util.Collection;

public class NullFlawOption implements FlawOption {

	@Override
	public Collection<String> getSuggestions() {
		return new ArrayList<>();
	}
	
	@Override
	public String toString() {
	  return "";
	}

  @Override
  public String getId() {
    return "";
  }

}