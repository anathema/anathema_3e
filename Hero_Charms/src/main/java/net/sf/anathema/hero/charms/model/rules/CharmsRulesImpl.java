package net.sf.anathema.hero.charms.model.rules;

import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.charm.data.reference.CategoryReference;
import net.sf.anathema.charm.data.reference.CharmName;
import net.sf.anathema.hero.charms.template.model.CharmsTemplate;
import net.sf.anathema.hero.concept.model.concept.CasteType;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

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
  public List<CategoryReference> getNativeCategories() {
    List<CategoryReference> references = new ArrayList<>();
    for (String categoryText : template.nativeCategories) {
      references.add(new CategoryReference(categoryText));
    }
    return references;
  }

  @Override
  public boolean isCompulsiveCharm(Charm charm) {
    return template.compulsiveCharms.contains(charm.getName().text);
  }

  @Override
  public void forAllCompulsiveCharms(Consumer<? super CharmName> consumer) {
    Stream<String> templateIds = template.compulsiveCharms.stream();
    templateIds.map(CharmName::new).forEach(consumer);
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