package net.sf.anathema.library.model.property;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AbstractOptionalPropertyOption implements OptionalPropertyOption {

  protected final List<String> suggestions = new ArrayList<>();
  
  @Override
  public Collection<String> getSuggestions() {
    return suggestions;
  }

}
