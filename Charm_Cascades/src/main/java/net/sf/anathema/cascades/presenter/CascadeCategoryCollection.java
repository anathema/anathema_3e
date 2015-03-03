package net.sf.anathema.cascades.presenter;

import net.sf.anathema.charm.data.reference.CategoryReference;
import net.sf.anathema.hero.charms.compiler.CharmProvider;
import net.sf.anathema.hero.charms.display.model.CategoryCollection;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class CascadeCategoryCollection implements CategoryCollection  {
  private CharmProvider charmProvider;

  public CascadeCategoryCollection(CharmProvider charmProvider) {
    this.charmProvider = charmProvider;
  }

  @Override
  public List<CategoryReference> getCurrentCategories() {
  	Set<CategoryReference> set = new LinkedHashSet<>();
    for (CategoryReference category : charmProvider.getAllCategories()) {
      if (!charmProvider.getCharms(category).isEmpty()) {
        set.add(category);
      }
    }
    return new ArrayList<>(set);
  }
}