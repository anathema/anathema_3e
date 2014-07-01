package net.sf.anathema.hero.charms.display.model;

import net.sf.anathema.charm.data.reference.CategoryReference;
import net.sf.anathema.hero.framework.type.CharacterType;
import net.sf.anathema.hero.magic.charm.martial.MartialArtsUtilities;

import java.util.ArrayList;
import java.util.List;

import static net.sf.anathema.hero.magic.charm.martial.MartialArtsUtilities.MARTIAL_ARTS;

public class CharacterCategoryCollection implements CategoryCollection {

  private CharmDisplayModel model;

  public CharacterCategoryCollection(CharmDisplayModel model) {
    this.model = model;
  }

  @Override
  public List<CategoryReference> getCurrentCategories() {
    List<CategoryReference> categoryReferences = new ArrayList<>();
    boolean alienCharms = model.isAllowedAlienCharms();
    for(CharacterType type : model.getCharmModel().getCharacterTypes(alienCharms)) {
      categoryReferences.add(MartialArtsUtilities.getCategory(type));
    }
    categoryReferences.add(MartialArtsUtilities.getCategory(MARTIAL_ARTS));
    return categoryReferences;
  }
}
