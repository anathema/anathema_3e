package net.sf.anathema.cascades.presenter;

import net.sf.anathema.charm.data.martial.MartialArtsUtilities;
import net.sf.anathema.charm.data.reference.CategoryReference;
import net.sf.anathema.hero.charms.compiler.CharmProvider;
import net.sf.anathema.hero.charms.display.model.CategoryCollection;
import net.sf.anathema.hero.environment.CharacterTypes;
import net.sf.anathema.hero.individual.splat.CharacterType;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static net.sf.anathema.charm.data.martial.MartialArtsUtilities.MARTIAL_ARTS;
import static net.sf.anathema.charm.data.martial.MartialArtsUtilities.getCategory;

public class CascadeCategoryCollection implements CategoryCollection  {
  private final CharacterTypes characterTypes;
  private CharmProvider charmProvider;

  public CascadeCategoryCollection(CharacterTypes characterTypes, CharmProvider charmProvider) {
    this.charmProvider = charmProvider;
    this.characterTypes = characterTypes;
  }

  @Override
  public List<CategoryReference> getCurrentCategories() {
    List<CategoryReference> categoryReferences = new ArrayList<>();
    categoryReferences.addAll(getCurrentCharacterTypes());
    categoryReferences.add(MartialArtsUtilities.getCategory(MARTIAL_ARTS));
    return categoryReferences;
  }

  private List<CategoryReference> getCurrentCharacterTypes() {
    Set<CategoryReference> set = new LinkedHashSet<>();
    for (CharacterType type : characterTypes) {
      CategoryReference categoryReference = getCategory(type);
      if (charmProvider.getCharms(categoryReference).length > 0) {
        set.add(categoryReference);
      }
    }
    return new ArrayList<>(set);
  }
}