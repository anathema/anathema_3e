package net.sf.anathema.hero.charms.model.rules;

import net.sf.anathema.charm.data.reference.CategoryReference;
import net.sf.anathema.hero.charms.template.model.CharmsTemplate;
import net.sf.anathema.hero.concept.CasteType;

public class CharmsRulesImpl implements CharmsRules {

  private CharmsTemplate template;

  public CharmsRulesImpl(CharmsTemplate template) {
    this.template = template;
  }

  @Override
  public MartialArtsRules getMartialArtsRules() {
    return new MartialArtsRulesImpl(template.martialArts);
  }

  @Override
  public boolean isNative(CategoryReference category) { return template.nativeCategories.contains(category.text); }

  @Override
  public boolean isAllowedAlienCharms(CasteType caste) {
    return template.alienCharmCastes.contains(caste.getId());
  }
}