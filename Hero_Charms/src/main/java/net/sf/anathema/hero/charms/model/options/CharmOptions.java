package net.sf.anathema.hero.charms.model.options;

import net.sf.anathema.charm.data.reference.CategoryReference;
import net.sf.anathema.hero.charms.compiler.CharmProvider;
import net.sf.anathema.hero.charms.model.CharmIdMap;
import net.sf.anathema.hero.charms.model.CharmTree;
import net.sf.anathema.hero.charms.model.GroupedCharmIdMap;
import net.sf.anathema.hero.charms.model.rules.CharmsRules;
import net.sf.anathema.hero.charms.model.special.ISpecialCharm;
import net.sf.anathema.hero.concept.CasteType;
import net.sf.anathema.hero.concept.HeroConcept;
import net.sf.anathema.hero.concept.HeroConceptFetcher;
import net.sf.anathema.hero.framework.type.CharacterType;
import net.sf.anathema.hero.framework.type.CharacterTypes;
import net.sf.anathema.hero.magic.charm.Charm;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.template.NativeCharacterType;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static net.sf.anathema.charm.old.attribute.CharmAttributeList.EXCLUSIVE_ATTRIBUTE;
import static net.sf.anathema.hero.charms.model.options.CharmTreeCategoryImpl.CreateFor;
import static net.sf.anathema.hero.magic.charm.martial.MartialArtsUtilities.*;

public class CharmOptions implements Iterable<CharmTreeCategory> {

  private final CharmsRules charmsRules;
  private final List<CharmTreeCategory> treeCategories = new ArrayList<>();
  private final Hero hero;
  private final CharmProvider charmProvider;
  private final CharmOptionRules optionRules;

  public CharmOptions(CharmProvider charmProvider, CharmsRules charmsRules, Hero hero, CharacterTypes characterTypes) {
    this.charmProvider = charmProvider;
    this.hero = hero;
    this.charmsRules = charmsRules;
    this.optionRules = new CharmOptionRulesImpl(charmsRules, characterTypes);
    for(CategoryReference reference : optionRules.getAllCategories()) {
      CharmTreeCategory treeCategory = CreateFor(optionRules, charmProvider, reference);
      if (!treeCategory.isEmpty()) {
        treeCategories.add(treeCategory);
      }
    }
  }

  public CharmIdMap getCharmIdMap() {
    return new GroupedCharmIdMap(new ArrayList<>(treeCategories));
  }

  public ISpecialCharm[] getSpecialCharms() {
    CategoryReference primaryCategory = getCategory(getNativeCharacterType());
    return charmProvider.getSpecialCharms(optionRules, getCharmIdMap(), primaryCategory);
  }

  public boolean isAlienCategory(CategoryReference category) {
    return !optionRules.getNativeCategories().contains(category);
  }

  public boolean isAlienCharm(Charm charm) {
    // todo (sandra): rewrite as soon as (non-exclusive) martial arts charms no longer have charm type
    String category = isMartialArts(charm) ? MARTIAL_ARTS.getId() : charm.getNativeCharacterType().getId();
    return isAlienCategory(new CategoryReference(category));
  }

  public Charm[] getCharms(CharmTree tree) {
    Charm[] allCharms = tree.getAllCharms();
    if (characterMayLearnAlienCharms()) {
      return allCharms;
    }
    return charmsThatAreNativeOrNotExclusive(allCharms);
  }

  @Override
  public Iterator<CharmTreeCategory> iterator() {
    return treeCategories.iterator();
  }

  public List<CategoryReference> getValidCategoryReferencesForHero() {
    if (isAlienCharmAllowed()) {
      return getNonEmptyReferences();
    }
    return optionRules.getNativeCategories();
  }

  public boolean isAlienCharmAllowed() {
    CasteType caste = HeroConceptFetcher.fetch(hero).getCaste().getType();
    return charmsRules.isAllowedAlienCharms(caste);
  }

  private CharacterType getNativeCharacterType() {
    return NativeCharacterType.get(hero);
  }

  private Charm[] charmsThatAreNativeOrNotExclusive(Charm[] allCharms) {
    List<Charm> charms = new ArrayList<>();
    for (Charm charm : allCharms) {
      if (!charm.hasAttribute(EXCLUSIVE_ATTRIBUTE)) {
        charms.add(charm);
      }
      if (isNativeCharm(charm)) {
        charms.add(charm);
      }
    }
    return charms.toArray(new Charm[charms.size()]);
  }

  private boolean isNativeCharm(Charm charm) { return !isAlienCharm(charm); }

  private boolean characterMayLearnAlienCharms() {
    HeroConcept concept = HeroConceptFetcher.fetch(hero);
    return charmsRules.isAllowedAlienCharms(concept.getCaste().getType());
  }

  private List<CategoryReference> getNonEmptyReferences() {
    List<CategoryReference> references = new ArrayList<>();
    for(CharmTreeCategory category : treeCategories) {
      references.add(category.getReference());
    }
    return references;
  }
}
