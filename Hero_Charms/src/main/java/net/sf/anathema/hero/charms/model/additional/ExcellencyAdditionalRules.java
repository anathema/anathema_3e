package net.sf.anathema.hero.charms.model.additional;

import net.sf.anathema.hero.abilities.model.AbilitiesModel;
import net.sf.anathema.hero.abilities.model.AbilitiesModelFetcher;
import net.sf.anathema.hero.charms.model.CharmsModel;
import net.sf.anathema.hero.charms.model.CharmsModelFetcher;
import net.sf.anathema.hero.charms.model.learn.CharmLearnAdapter;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.individual.splat.HeroType;
import net.sf.anathema.hero.traits.model.Trait;
import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.hero.traits.model.state.TraitStateChangedListener;
import net.sf.anathema.hero.traits.model.state.TraitStateType;
import net.sf.anathema.library.event.IntegerChangedListener;
import net.sf.anathema.magic.data.Charm;
import net.sf.anathema.magic.data.reference.CharmName;

import java.util.ArrayList;
import java.util.List;

import static net.sf.anathema.hero.traits.model.state.CasteTraitStateType.Caste;
import static net.sf.anathema.hero.traits.model.state.FavoredTraitStateType.Favored;

//Called via Object Factory in CharmModel
@SuppressWarnings("unused")
public class ExcellencyAdditionalRules implements AdditionalCharmRules {

  private final static String id = "LearnsExcellenciesForFavoredAbilitiesAndWithOtherCharms";

  private final HeroType heroType;
  private final CharmsModel charms;
  private final AbilitiesModel traits;

  public ExcellencyAdditionalRules(Hero hero) {
    this.heroType = hero.getSplat().getTemplateType().getHeroType();
    this.charms = CharmsModelFetcher.fetch(hero);
    this.traits = AbilitiesModelFetcher.fetch(hero);
  }

  protected String getStringForExcellency(String trait) {
    return heroType.getId() + ".Excellent" + heroType.getId() + trait;
  }

  @Override
  public String getId() {
    return id;
  }

  @Override
  public void initialize() {
    charms.addCharmLearnListener(new CharmExcellencyMonitor());
    traits.getAll().forEach(trait -> {
      TraitExcellencyMonitor monitor = new TraitExcellencyMonitor(trait.getType());
      traits.getState(trait).addTraitStateChangedListener(monitor);
      trait.addCurrentValueListener(monitor);
    });
  }

  private void updateExcellency(TraitType type) {
    if (shouldKnowExcellency(type)) {
      learnExcellency(type);
    } else {
      forgetExcellency(type);
    }
  }

  private boolean shouldKnowExcellency(TraitType traitType) {
    return isProficientInCasteOrFavored(traitType) || hasLearnedOneOrMoreCharms(traitType);
  }

  private boolean isProficientInCasteOrFavored(TraitType traitType) {
    Trait trait = traits.getTrait(traitType);
    TraitStateType state = traits.getState(trait).getType();
    boolean isCasteOrFavored = state.countsAs(Caste) || state.countsAs(Favored);
    return isCasteOrFavored && trait.getCurrentValue() >= 1;
  }

  private boolean hasLearnedOneOrMoreCharms(TraitType traitType) {
    List<TraitType> traitTypes = new ArrayList<>();
    traitTypes.add(traitType);
    return charms.hasLearnedThresholdCharmsOfTrait(traitTypes, null, 1, 1);
  }

  private void forgetExcellency(TraitType type) {
    CharmName excellencyName = new CharmName(getStringForExcellency(type.getId()));
    if (!charms.exists(excellencyName)) {
      return;
    }
    Charm excellency = charms.getCharmById(excellencyName);
    if (charms.isLearned(excellency)) {
      charms.getLearningModel().forgetCharm(excellency, false);
    }
  }

  private void learnExcellency(TraitType type) {
    CharmName excellencyName = new CharmName(getStringForExcellency(type.getId()));
    if (!charms.exists(excellencyName)) {
      return;
    }
    Charm excellency = charms.getCharmById(excellencyName);
    if (!charms.isLearned(excellency)) {
      charms.getLearningModel().learnCharm(excellency, false);
    }
  }

  private class TraitExcellencyMonitor implements TraitStateChangedListener, IntegerChangedListener {

    private TraitType type;

    public TraitExcellencyMonitor(TraitType type) {
      this.type = type;
    }

    @Override
    public void favorableStateChanged(TraitStateType newState) {
      updateExcellency(type);
    }

    @Override
    public void valueChanged(int newValue) {
      updateExcellency(type);
    }
  }


  private class CharmExcellencyMonitor extends CharmLearnAdapter {

    @Override
    public void charmLearned(Charm charm) {
      updateExcellency(new TraitType(charm.getPrerequisites().getPrimaryTraitType().type));
    }

    @Override
    public void charmForgotten(Charm charm) {
      updateExcellency(new TraitType(charm.getPrerequisites().getPrimaryTraitType().type));
    }
  }
}