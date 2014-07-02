package net.sf.anathema.hero.charms.display.model;

import net.sf.anathema.charm.data.reference.CategoryReference;
import net.sf.anathema.charm.data.reference.CharmName;
import net.sf.anathema.hero.charms.model.CharmsModel;
import net.sf.anathema.hero.charms.model.CharmsModelFetcher;
import net.sf.anathema.hero.charms.model.learn.LearningCharmTree;
import net.sf.anathema.hero.concept.CasteSelection;
import net.sf.anathema.hero.concept.HeroConceptFetcher;
import net.sf.anathema.hero.magic.charm.Charm;
import net.sf.anathema.hero.magic.description.MagicDescriptionProvider;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.lib.control.ChangeListener;

import java.util.List;

public class CharmDisplayModel {
  private Hero hero;
  private MagicDescriptionProvider magicDescriptionProvider;

  public CharmDisplayModel(Hero hero, MagicDescriptionProvider magicDescriptionProvider) {
    this.hero = hero;
    this.magicDescriptionProvider = magicDescriptionProvider;
  }

  public boolean isAllowedAlienCharms() {
    return getCharmModel().getOptions().isAlienCharmsAllowedForHero();
  }

  public void addCasteChangeListener(ChangeListener listener) {
    getCaste().addChangeListener(listener);
  }

  public CharmsModel getCharmModel() {
    return CharmsModelFetcher.fetch(hero);
  }

  private CasteSelection getCaste() {
    return HeroConceptFetcher.fetch(hero).getCaste();
  }

  public void toggleLearned(CharmName charmId) {
    CharmsModel charms = getCharmModel();
    LearningCharmTree charmGroup = getCharmGroupByCharmId(charmId);
    Charm charmToLearn = charms.getCharmById(charmId);
    charmGroup.toggleLearned(charmToLearn);
  }

  private LearningCharmTree getCharmGroupByCharmId(CharmName charmId) {
    CharmsModel charms = getCharmModel();
    Charm charm = charms.getCharmById(charmId);
    return charms.getGroup(charm);
  }

  public MagicDescriptionProvider getMagicDescriptionProvider() {
    return magicDescriptionProvider;
  }

  public List<CategoryReference> getValidCategoriesForHero() {
    return getCharmModel().getOptions().getValidCategoryReferencesForHero();
  }
}
