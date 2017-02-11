package net.sf.anathema.hero.charms.model.options;

import net.sf.anathema.hero.charms.compiler.CharmProvider;
import net.sf.anathema.hero.charms.model.CharmMap;
import net.sf.anathema.hero.charms.model.CharmTree;
import net.sf.anathema.hero.charms.model.GroupedCharmMap;
import net.sf.anathema.hero.charms.model.rules.CharmsRules;
import net.sf.anathema.hero.charms.model.special.CharmSpecialLearning;
import net.sf.anathema.hero.charms.model.special.CharmSpecialMechanic;
import net.sf.anathema.hero.concept.model.concept.HeroConcept;
import net.sf.anathema.hero.concept.model.concept.HeroConceptFetcher;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.individual.model.NativeCharacterType;
import net.sf.anathema.magic.data.Charm;
import net.sf.anathema.magic.data.reference.CategoryReference;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import static net.sf.anathema.hero.charms.model.options.CharmTreeCategoryImpl.CreateFor;

public class CharmOptionsImpl implements Iterable<CharmTreeCategory>, CharmOptions {

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
    for (CategoryReference reference : charmProvider.getAllCategories()) {
      CharmTreeCategory treeCategory = CreateFor(optionsCheck, charmProvider, reference);
      if (!treeCategory.isEmpty()) {
        treeCategories.add(treeCategory);
      }
    }
  }

  @Override
  public CharmMap getCharmIdMap() {
    return new GroupedCharmMap(new ArrayList<>(treeCategories));
  }

  @Override
  public Collection<CharmSpecialLearning> getSpecialLearningCharms() {
    List<CharmSpecialLearning> relevantCharms = new ArrayList<>();
    CharmSpecialLearning[] allSpecialCharms = getAllSpecialLearningCharms();
    for (CharmSpecialLearning specialCharm : allSpecialCharms) {
      Charm charm = getCharmIdMap().getCharmById(specialCharm.getCharmName());
      if (charm != null && optionsCheck.isValidOptionForHeroType(charm)) {
        relevantCharms.add(specialCharm);
      }
    }
    return relevantCharms;
  }

  private CharmSpecialLearning[] getAllSpecialLearningCharms() {
    CategoryReference preferredCategory = new CategoryReference(NativeCharacterType.get(hero).getId());
    SpecialCharmSet set = new SpecialCharmSet();
    for (CategoryReference type : charmProvider.getAllCategories()) {
      set.addAll(charmProvider.getSpecialLearningCharms(type));
    }
    for (CharmSpecialLearning preferredCharm : charmProvider.getSpecialLearningCharms(preferredCategory)) {
      set.add(preferredCharm);
    }
    return set.toArray(new CharmSpecialLearning[set.size()]);
  }

  @Override
  public Collection<Charm> filterAvailableCharms(CharmTree tree) {
    Collection<Charm> allCharms = tree.getAllCharms();
    if (isAlienCharmsAllowedForHero()) {
      return allCharms;
    }
    return collectCharmsThatAreNative(allCharms);
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

  private Collection<Charm> collectCharmsThatAreNative(Collection<Charm> allCharms) {
    List<Charm> charms = new ArrayList<>();
    for (Charm charm : allCharms) {
      if (!charmsRule.isAlienCharm(charm)) {
        charms.add(charm);
      }
    }
    return charms;
  }

  @Override
  public boolean isAlienCharmsAllowedForHero() {
    HeroConcept concept = HeroConceptFetcher.fetch(hero);
    return charmsRule.isAllowedAlienCharms(concept.getCaste().getType());
  }

  public void initSpecialMechanics() {
    getAllMechanics().forEach(mechanic -> mechanic.initialize(hero));
  }

  private Collection<CharmSpecialMechanic> getAllMechanics() {
    List<CharmSpecialMechanic> mechanics = new ArrayList<>();
    for (Charm mechanicalCharm : charmProvider.getCharmsWithSpecialMechanics()) {
      mechanics.addAll(charmProvider.getSpecialMechanicsForCharm(mechanicalCharm.getName()));
    }
    return mechanics;
  }

  @Override
  public boolean isAllowedAnyCharms() {
    return charmsRule.isAllowedAnyCharms();
  }
}
