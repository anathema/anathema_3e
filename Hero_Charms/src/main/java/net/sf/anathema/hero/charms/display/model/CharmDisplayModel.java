package net.sf.anathema.hero.charms.display.model;

import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.charm.data.reference.CategoryReference;
import net.sf.anathema.charm.data.reference.CharmName;
import net.sf.anathema.hero.charms.model.CharmsModel;
import net.sf.anathema.hero.charms.model.CharmsModelFetcher;
import net.sf.anathema.hero.charms.model.learn.LearningModel;
import net.sf.anathema.hero.concept.model.concept.CasteSelection;
import net.sf.anathema.hero.concept.model.concept.HeroConceptFetcher;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.library.event.ChangeListener;
import net.sf.anathema.magic.description.model.MagicDescriptionProvider;

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
    LearningModel charmGroup = getCharmGroupByCharmId(charmId);
    Charm charmToLearn = charms.getCharmById(charmId);
    charmGroup.toggleLearned(charmToLearn);
  }

  private LearningModel getCharmGroupByCharmId(CharmName charmId) {
    CharmsModel charms = getCharmModel();
    Charm charm = charms.getCharmById(charmId);
    return charms.getLearningModel();
  }

  public MagicDescriptionProvider getMagicDescriptionProvider() {
    return magicDescriptionProvider;
  }

  public List<CategoryReference> getValidCategoriesForHero() {
    return getCharmModel().getOptions().getValidCategoryReferencesForHero();
  }
}
