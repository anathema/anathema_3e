package net.sf.anathema.hero.charms.model.options;

import net.sf.anathema.charm.data.reference.CategoryReference;
import net.sf.anathema.hero.charms.compiler.CharmProvider;
import net.sf.anathema.hero.charms.compiler.SpecialCharmSet;
import net.sf.anathema.hero.charms.model.CharmIdMap;
import net.sf.anathema.hero.charms.model.CharmTree;
import net.sf.anathema.hero.charms.model.GroupedCharmIdMap;
import net.sf.anathema.hero.charms.model.rules.CharmsRules;
import net.sf.anathema.hero.charms.model.special.ISpecialCharm;
import net.sf.anathema.hero.concept.HeroConcept;
import net.sf.anathema.hero.concept.HeroConceptFetcher;
import net.sf.anathema.hero.framework.type.CharacterType;
import net.sf.anathema.hero.magic.charm.Charm;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.template.NativeCharacterType;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static net.sf.anathema.charm.old.attribute.CharmAttributeList.EXCLUSIVE_ATTRIBUTE;
import static net.sf.anathema.hero.charms.model.options.CharmTreeCategoryImpl.CreateFor;
import static net.sf.anathema.hero.magic.charm.martial.MartialArtsUtilities.getCategory;

public class CharmOptionsImpl implements Iterable<CharmTreeCategory>,CharmOptions {

  private final List<CharmTreeCategory> treeCategories = new ArrayList<>();
  private final CharmsRules charmsRule;
  private final Hero hero;
  private final CharmProvider charmProvider;
  private final CharmOptionCheck optionsCheck;

  public CharmOptionsImpl(CharmProvider charmProvider, CharmsRules charmsRule, Hero hero) {
    this.charmProvider = charmProvider;
    this.hero = hero;
    this.charmsRule = charmsRule;
    this.optionsCheck = new CharmOptionCheckImpl(charmsRule);
    for(CategoryReference reference : charmProvider.getAllCategories()) {
      CharmTreeCategory treeCategory = CreateFor(optionsCheck, charmProvider, reference);
      if (!treeCategory.isEmpty()) {
        treeCategories.add(treeCategory);
      }
    }
  }

  @Override
  public CharmIdMap getCharmIdMap() {
    return new GroupedCharmIdMap(new ArrayList<>(treeCategories));
  }

  @Override
  public ISpecialCharm[] getSpecialCharms() {
    List<ISpecialCharm> relevantCharms = new ArrayList<>();
    ISpecialCharm[] allSpecialCharms = getAllSpecialCharms();
    for (ISpecialCharm specialCharm : allSpecialCharms) {
      Charm charm = getCharmIdMap().getCharmById(specialCharm.getCharmId());
      if (charm != null && optionsCheck.isValidOptionForHeroType(charm)) {
        relevantCharms.add(specialCharm);
      }
    }
    return relevantCharms.toArray(new ISpecialCharm[relevantCharms.size()]);
  }

  private ISpecialCharm[] getAllSpecialCharms() {
    CategoryReference preferredCategory = getCategory(getNativeCharacterType());
    SpecialCharmSet set = new SpecialCharmSet();
    for (CategoryReference type : charmProvider.getAllCategories()) {
      set.add(charmProvider.getSpecialCharms(type));
    }
    for (ISpecialCharm preferredCharm : charmProvider.getSpecialCharms(preferredCategory)) {
      set.add(preferredCharm);
    }
    return set.toArray(new ISpecialCharm[set.size()]);
  }

  @Override
  public Charm[] getCharms(CharmTree tree) {
    Charm[] allCharms = tree.getAllCharms();
    if (isAlienCharmsAllowedForHero()) {
      return allCharms;
    }
    return collectCharmsThatAreNativeOrNotExclusive(allCharms);
  }

  @Override
  public Iterator<CharmTreeCategory> iterator() {
    return treeCategories.iterator();
  }

  @Override
  public List<CategoryReference> getValidCategoryReferencesForHero() {
    if (isAlienCharmsAllowedForHero()) {
      return charmProvider.getAllCategories();
    }
    return charmsRule.getNativeCategories();
  }

  private CharacterType getNativeCharacterType() {
    return NativeCharacterType.get(hero);
  }

  private Charm[] collectCharmsThatAreNativeOrNotExclusive(Charm[] allCharms) {
    List<Charm> charms = new ArrayList<>();
    for (Charm charm : allCharms) {
      if (!charm.hasAttribute(EXCLUSIVE_ATTRIBUTE)) {
        charms.add(charm);
      }
      if (!charmsRule.isAlienCharm(charm)) {
        charms.add(charm);
      }
    }
    return charms.toArray(new Charm[charms.size()]);
  }

  @Override
  public boolean isAlienCharmsAllowedForHero() {
    HeroConcept concept = HeroConceptFetcher.fetch(hero);
    return charmsRule.isAllowedAlienCharms(concept.getCaste().getType());
  }
}
