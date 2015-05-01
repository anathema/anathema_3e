package net.sf.anathema.hero.spiritual.model.traits;

import net.sf.anathema.hero.environment.HeroEnvironment;
import net.sf.anathema.hero.experience.model.ExperienceModelFetcher;
import net.sf.anathema.hero.individual.change.ChangeAnnouncer;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.individual.model.HeroModel;
import net.sf.anathema.hero.spiritual.template.SpiritualTraitsTemplate;
import net.sf.anathema.hero.traits.model.DefaultTraitMap;
import net.sf.anathema.hero.traits.model.Trait;
import net.sf.anathema.hero.traits.model.TraitImpl;
import net.sf.anathema.hero.traits.model.TraitLimitation;
import net.sf.anathema.hero.traits.model.TraitModel;
import net.sf.anathema.hero.traits.model.TraitModelFetcher;
import net.sf.anathema.hero.traits.model.TraitRules;
import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.hero.traits.model.event.TraitValueChangedListener;
import net.sf.anathema.hero.traits.model.rules.TraitRulesImpl;
import net.sf.anathema.hero.traits.template.TraitTemplate;
import net.sf.anathema.library.identifier.Identifier;
import net.sf.anathema.points.model.PointModelFetcher;
import net.sf.anathema.points.model.PointsModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static net.sf.anathema.hero.traits.model.types.CommonTraitTypes.Essence;
import static net.sf.anathema.hero.traits.model.types.CommonTraitTypes.Willpower;

public class SpiritualTraitModelImpl extends DefaultTraitMap implements SpiritualTraitModel, HeroModel {

  private final SpiritualTraitsTemplate template;
  private TraitModel traitModel;

  public SpiritualTraitModelImpl(SpiritualTraitsTemplate template) {
    this.template = template;
  }

  @Override
  public Identifier getId() {
    return ID;
  }

  @Override
  public void initialize(HeroEnvironment environment, Hero hero) {
    this.traitModel = TraitModelFetcher.fetch(hero);
    addEssence(hero);
    addWillpower(hero);
    getTrait(Essence).addCurrentValueListener(new EssenceLimitationListener(traitModel, hero));
    initExperienceListening(hero);
  }

  private void initExperienceListening(Hero hero) {
    PointsModel pointsModel = PointModelFetcher.fetch(hero);
    List<Integer> experienceBoundsDescending = getExperienceBoundsInDescendingOrder();
    pointsModel.getExperiencePoints().addExperiencePointConfigurationListener(() -> updateEssence(pointsModel, experienceBoundsDescending));
    hero.getChangeAnnouncer().addListener(flavor -> {
      if (ExperienceModelFetcher.fetch(hero).isExperienced()) {
        updateEssence(pointsModel, experienceBoundsDescending);
      }
    });
  }

  private void updateEssence(PointsModel pointsModel, List<Integer> experienceBoundsDescending) {
    Trait essence = getTrait(Essence);
    int totalGained = pointsModel.getExperiencePoints().getTotalExperiencePoints();
    int totalSpent = pointsModel.getExperiencePointManagement().getTotalCosts();
    for (Integer bound : experienceBoundsDescending) {
      if (totalGained >= bound && totalSpent >= bound) {
        essence.setCurrentValue(template.essenceValues.get(String.valueOf(bound)));
        return;
      }
    }
  }

  private List<Integer> getExperienceBoundsInDescendingOrder() {
    List<String> experienceBounds = new ArrayList<>(template.essenceValues.keySet());
    Collections.reverse(experienceBounds);
    return experienceBounds.stream().map(Integer::valueOf).collect(toList());
  }

  @Override
  public void initializeListening(ChangeAnnouncer announcer) {
    for (Trait trait : getAll()) {
      trait.addCurrentValueListener(new TraitValueChangedListener(announcer, trait));
    }
  }

  private void addEssence(Hero hero) {
    createTrait(hero, Essence, template.essence);
  }

  private void addWillpower(Hero hero) {
    createTrait(hero, Willpower, template.willpower);
  }

  @Override
  public int getEssenceCap(boolean modified) {
    Trait essence = getTrait(Essence);
    return modified ? essence.getModifiedMaximalValue() : essence.getUnmodifiedMaximalValue();
  }

  @Override
  public TraitLimitation getEssenceLimitation() {
    return traitModel.createLimitation(template.essence.limitation);
  }

  private void createTrait(Hero hero, TraitType type, TraitTemplate template) {
    TraitRules rules = new TraitRulesImpl(type, template, hero);
    Trait trait = new TraitImpl(hero, rules);
    addTraits(trait);
    traitModel.addTraits(trait);
  }
}
