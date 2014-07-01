package net.sf.anathema.hero.charms.model.options;

import net.sf.anathema.charm.data.reference.TreeCategoryReference;
import net.sf.anathema.hero.charms.compiler.CharmProvider;
import net.sf.anathema.hero.charms.model.CharmHasSameTypeAsCharacter;
import net.sf.anathema.hero.charms.model.CharmIdMap;
import net.sf.anathema.hero.charms.model.CharmTree;
import net.sf.anathema.hero.charms.model.GroupedCharmIdMap;
import net.sf.anathema.hero.charms.model.rules.CharmsRules;
import net.sf.anathema.hero.charms.model.special.ISpecialCharm;
import net.sf.anathema.hero.concept.HeroConcept;
import net.sf.anathema.hero.concept.HeroConceptFetcher;
import net.sf.anathema.hero.framework.type.CharacterType;
import net.sf.anathema.hero.framework.type.CharacterTypes;
import net.sf.anathema.hero.magic.charm.Charm;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.template.NativeCharacterType;

import java.util.*;

import static net.sf.anathema.charm.old.attribute.CharmAttributeList.EXCLUSIVE_ATTRIBUTE;
import static net.sf.anathema.hero.charms.model.options.CharmTreeCategoryImpl.ForMartialArts;
import static net.sf.anathema.hero.charms.model.options.CharmTreeCategoryImpl.ForNonMartialArts;
import static net.sf.anathema.hero.magic.charm.martial.MartialArtsUtilities.*;

public class CharmOptions implements Iterable<CharmTreeCategory> {

  private final CharmsRules charmsRules;
  private final CharacterTypeList availableTypes;
  private final Map<CharacterType, CharmTreeCategory> treesByType = new HashMap<>();
  private final Hero hero;
  private final CharmTreeCategory martialArtsCharmTree;
  private final CharmProvider charmProvider;
  private final CharmOptionCheck optionCheck;

  public CharmOptions(CharmProvider charmProvider, CharmsRules charmsRules, Hero hero, CharacterTypes characterTypes) {
    this.charmProvider = charmProvider;
    this.hero = hero;
    this.charmsRules = charmsRules;
    this.optionCheck = new CharmOptionRulesImpl(hero, charmsRules, characterTypes);
    this.martialArtsCharmTree = ForMartialArts(optionCheck, charmProvider);
    this.availableTypes = new CharacterTypeList(charmProvider);
    availableTypes.collectAvailableTypes(getNativeCharacterType(), characterTypes);
    for (CharacterType type : availableTypes) {
      treesByType.put(type,  ForNonMartialArts(optionCheck, charmProvider, type));
    }
  }

  public CharmIdMap getCharmIdMap() {
    List<CharmIdMap> trees = new ArrayList<>();
    trees.addAll(treesByType.values());
    trees.add(martialArtsCharmTree);
    return new GroupedCharmIdMap(trees);
  }

  public ISpecialCharm[] getSpecialCharms() {
    return charmProvider.getSpecialCharms(optionCheck, getCharmIdMap(), getNativeCharacterType());
  }

  public boolean isAlienType(TreeCategoryReference category) {
    List<String> nativeCategories = Arrays.asList(getNativeCharacterType().getId(), MARTIAL_ARTS.getId());
    return !nativeCategories.contains(category.text);
  }

  public CharacterType[] getCharacterTypes(boolean includeAlienTypes) {
    if (!includeAlienTypes) {
      return new CharacterType[]{getNativeCharacterType()};
    }
    return availableTypes.asArray();
  }

  public boolean isAlienCharm(Charm charm) {
    String category = isMartialArts(charm) ? MARTIAL_ARTS.getId() : charm.getCharacterType().getId();
    return isAlienType(new TreeCategoryReference(category));
  }

  public Charm[] getCharms(CharmTree tree) {
    Charm[] allCharms = tree.getAllCharms();
    if (characterMayLearnAlienCharms()) {
      return allCharms;
    }
    return charmsThatAreNativeOrNotExclusive(allCharms);
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

  private boolean isNativeCharm(Charm charm) {
    return new CharmHasSameTypeAsCharacter(hero).apply(charm);
  }

  private boolean characterMayLearnAlienCharms() {
    HeroConcept concept = HeroConceptFetcher.fetch(hero);
    return charmsRules.isAllowedAlienCharms(concept.getCaste().getType());
  }

  @Override
  public Iterator<CharmTreeCategory> iterator() {
    List<CharmTreeCategory> categories = new ArrayList<>();
    categories.addAll(treesByType.values());
    categories.add(martialArtsCharmTree);
    return categories.iterator();
  }
}
