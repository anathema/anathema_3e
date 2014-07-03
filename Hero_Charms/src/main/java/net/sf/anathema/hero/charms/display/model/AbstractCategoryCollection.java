package net.sf.anathema.hero.charms.display.model;

import net.sf.anathema.charm.data.reference.CategoryReference;
import net.sf.anathema.charm.data.martial.MartialArtsUtilities;

import java.util.ArrayList;
import java.util.List;

import static net.sf.anathema.charm.data.martial.MartialArtsUtilities.MARTIAL_ARTS;

public abstract class AbstractCategoryCollection implements CategoryCollection {

  @Override
  public List<CategoryReference> getCurrentCategories() {
    List<CategoryReference> categoryReferences = new ArrayList<>();
    categoryReferences.addAll(getCurrentCharacterTypes());
    categoryReferences.add(MartialArtsUtilities.getCategory(MARTIAL_ARTS));
    return categoryReferences;
  }

  protected abstract List<CategoryReference> getCurrentCharacterTypes();
}
