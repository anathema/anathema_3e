package net.sf.anathema.hero.application.creation.models;

import net.sf.anathema.hero.individual.splat.ConfiguredModel;
import net.sf.anathema.library.identifier.Identifier;

import java.util.ArrayList;
import java.util.List;

public class SortedModelList {
  private List<ConfiguredModel> sortedModelIds = new ArrayList<>();

  public boolean contains(ConfiguredModel entry) {
    return sortedModelIds.contains(entry);
  }

  public boolean containsEntryWithId(Identifier id) {
    for (ConfiguredModel model : sortedModelIds) {
      if (id.equals(model.modelId)) {
        return true;
      }
    }
    return false;
  }
}
