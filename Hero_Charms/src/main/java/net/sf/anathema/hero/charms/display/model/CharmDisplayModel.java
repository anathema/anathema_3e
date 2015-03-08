package net.sf.anathema.hero.charms.display.model;

import net.sf.anathema.magic.data.Charm;
import net.sf.anathema.magic.data.reference.CategoryReference;
import net.sf.anathema.magic.data.reference.CharmName;
import net.sf.anathema.hero.charms.display.coloring.CharmBorderColorEvaluator;
import net.sf.anathema.hero.charms.model.CharmsModel;
import net.sf.anathema.hero.charms.model.CharmsModelFetcher;
import net.sf.anathema.hero.charms.model.CommonMagicAttributes;
import net.sf.anathema.hero.charms.model.learn.LearningModel;
import net.sf.anathema.hero.concept.model.concept.CasteSelection;
import net.sf.anathema.hero.concept.model.concept.HeroConceptFetcher;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.library.event.ChangeListener;
import net.sf.anathema.library.presenter.RGBColor;
import net.sf.anathema.magic.description.model.MagicDescriptionProvider;

import java.util.List;

public class CharmDisplayModel {
  private Hero hero;
  private MagicDescriptionProvider magicDescriptionProvider;
  private CharmBorderColorEvaluator evaluator;

  public CharmDisplayModel(Hero hero, CharmBorderColorEvaluator evaluator,
  		MagicDescriptionProvider magicDescriptionProvider) {
    this.hero = hero;
    this.magicDescriptionProvider = magicDescriptionProvider;
    this.evaluator = evaluator;
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
  
  public RGBColor getBorderColorForCharm(Charm charm) {
  	return evaluator.getBorderColorForCharm(charm);
  }

  private CasteSelection getCaste() {
    return HeroConceptFetcher.fetch(hero).getCaste();
  }

  public void toggleLearned(CharmName charmId) {
    CharmsModel charms = getCharmModel();
    LearningModel charmGroup = getCharmGroupByCharmId(charmId);
    Charm charmToLearn = charms.getCharmById(charmId);
    if (!charmToLearn.hasAttribute(CommonMagicAttributes.NO_MANUAL_CONTROL)) {
    	charmGroup.toggleLearned(charmToLearn);
    }
  }

  private LearningModel getCharmGroupByCharmId(CharmName charmId) {
    return getCharmModel().getLearningModel();
  }

  public MagicDescriptionProvider getMagicDescriptionProvider() {
    return magicDescriptionProvider;
  }

  public List<CategoryReference> getValidCategoriesForHero() {
    return getCharmModel().getOptions().getValidCategoryReferencesForHero();
  }
}
