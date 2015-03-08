package net.sf.anathema.hero.charms.model.rules;

import net.sf.anathema.magic.data.Charm;
import net.sf.anathema.magic.data.reference.CategoryReference;
import net.sf.anathema.hero.charms.template.model.CharmsTemplate;
import net.sf.anathema.hero.concept.model.concept.CasteType;

import java.util.ArrayList;
import java.util.List;

public class CharmsRulesImpl implements CharmsRules {

  private CharmsTemplate template;

  public CharmsRulesImpl(CharmsTemplate template) {
    this.template = template;
  }

  @Override
  public List<CategoryReference> getNativeCategories() {
    List<CategoryReference> references = new ArrayList<>();
    for (String categoryText : template.nativeCategories) {
      references.add(new CategoryReference(categoryText));
    }
    return references;
  }

  @Override
  public boolean isAllowedAlienCharms(CasteType caste) {
    return template.alienCharmCastes.contains(caste.getId());
  }

  public boolean isAlienCharm(Charm charm) {
    return isAlienCategory(charm.getTreeReference().category);
  }

  public boolean isAlienCategory(CategoryReference category) {
    return !getNativeCategories().contains(category);
  }
}