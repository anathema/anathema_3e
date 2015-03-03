package net.sf.anathema.library.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class AbstractOptionalEntryOption implements OptionalEntryOption {
  protected final List<String> suggestions = new ArrayList<>();
  
  @Override
  public Collection<String> getSuggestions() {
    return suggestions;
  }
}
