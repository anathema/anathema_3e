package net.sf.anathema.hero.charms.display.model;

import net.sf.anathema.charm.data.reference.CategoryReference;

import java.util.List;

public class CharacterCategoryCollection implements CategoryCollection {

  private CharmDisplayModel model;

  public CharacterCategoryCollection(CharmDisplayModel model) {
    this.model = model;
  }

  @Override
  public List<CategoryReference> getCurrentCategories() {
    List<CategoryReference> categories = model.getValidCategoriesForHero();
    return categories;
  }
}
