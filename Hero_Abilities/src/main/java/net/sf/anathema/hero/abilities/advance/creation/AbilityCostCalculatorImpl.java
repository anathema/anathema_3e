package net.sf.anathema.hero.abilities.advance.creation;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.sf.anathema.hero.abilities.advance.PointCalculationTraitHolder;
import net.sf.anathema.hero.traits.advance.CurrentRatingCost;
import net.sf.anathema.hero.traits.model.FavorableTraitCost;
import net.sf.anathema.hero.traits.model.Trait;
import net.sf.anathema.hero.traits.model.state.TraitState;
import net.sf.anathema.hero.traits.model.state.TraitStateType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static net.sf.anathema.hero.traits.advance.TraitCalculationUtilities.getCreationCalculationValue;

public class AbilityCostCalculatorImpl implements AbilityCostCalculator {

  private final PointCalculationTraitHolder traits;
  private final AbilityCreationData creationData;
  private final Multimap<Trait, FavorableTraitCost> costsByTrait = HashMultimap.create();
  private final Map<TraitStateType, Integer> statePicks = new HashMap<>();
  private int generalDotSum = 0;

  public AbilityCostCalculatorImpl(PointCalculationTraitHolder traits, AbilityCreationData creationData) {
    this.traits = traits;
    this.creationData = creationData;
    clearStatePickCounter();
  }

  protected int getCostFactor(Trait trait) {
    CurrentRatingCost abilityCosts = creationData.getAbilityCosts(isCheapened(trait));
    return abilityCosts.getRatingCosts(getCalculationBase(trait));
  }

  public void recalculate() {
    clear();
    countTraitStatePicks();
    Set<Trait> sortedTraits = sortTraitsByStatus();
    for (Trait trait : sortedTraits) {
      if (!trait.isDerived()) {
        int costFactor = getCostFactor(trait);
        Collection<FavorableTraitCost> allCosts;
        if (isCheapened(trait)) {
          allCosts = handleFavoredTrait(trait, costFactor);
        } else {
          allCosts = handleGeneralTrait(trait, costFactor);
        }
        costsByTrait.putAll(trait, allCosts);
      }
    }
  }

  private void clear() {
    clearStatePickCounter();
    generalDotSum = 0;
    costsByTrait.clear();
  }

  private void clearStatePickCounter() {
    for (TraitStateType candidate : traits.getAvailableTraitStates()) {
      statePicks.put(candidate, 0);
    }
  }

  private void countTraitStatePicks() {
    for (Trait trait : traits.getAll()) {
      TraitState state = traits.getState(trait);
      for (TraitStateType candidate : traits.getAvailableTraitStates()) {
        if (state.countsTowardsLimitsOn(candidate) && state.getType().countsAs(candidate)) increasePicksForType(candidate);
      }
    }
  }

  public int getBonusPointCost() {
    int bonusPointSum = 0;
    for (Trait trait : costsByTrait.keys()) {
      for (FavorableTraitCost cost : costsByTrait.get(trait)) {
        bonusPointSum += cost.getBonusCost();
      }
    }
    return bonusPointSum;
  }

  @Override
  public int getBonusPointsGranted() {
    return 0;
  }

  private int getDefaultDotCount() {
    return creationData.getGeneralDotCount();
  }

  @Override
  public int getPicksSpent(TraitStateType type) {
    return statePicks.get(type);
  }

  public int getFreePointsSpent() {
    return generalDotSum;
  }

  private FavorableTraitCost handleFavoredSingleTrait(Trait trait, int bonusPointCostFactor) {
    int freeTraitMax = Math.max(creationData.getMaximumFreeAbilityRank(), trait.getAbsoluteMinValue());
    int freePointsToAdd = Math.min(getCalculationBase(trait), freeTraitMax);
    int favoredDotsSpent = 0;
    int generalDotsSpent = 0;
    int bonusPointsSpent = 0;
    if (freePointsToAdd > 0) {
      if (generalDotSum < getDefaultDotCount()) {
        int remainingGeneralPoints = getDefaultDotCount() - generalDotSum;
        generalDotsSpent = Math.min(remainingGeneralPoints, freePointsToAdd);
        increaseGeneralDotSum(generalDotsSpent);
        freePointsToAdd -= generalDotsSpent;
      }
    }
    if (freePointsToAdd > 0) {
      if (generalDotSum < getDefaultDotCount()) {
        int remainingGeneralPoints = getDefaultDotCount() - generalDotSum;
        generalDotsSpent = Math.min(remainingGeneralPoints, freePointsToAdd);
        increaseGeneralDotSum(generalDotsSpent);
        freePointsToAdd -= generalDotsSpent;
      }
    }
    if (freePointsToAdd > 0) {
      bonusPointsSpent += freePointsToAdd * bonusPointCostFactor;
    }
    bonusPointsSpent += Math.max(getCalculationBase(trait) - freeTraitMax, 0) * bonusPointCostFactor;
    return new FavorableTraitCost(bonusPointsSpent, generalDotsSpent, favoredDotsSpent);
  }

  private Collection<FavorableTraitCost> handleFavoredTrait(Trait trait, final int bonusPointCostFactor) {
    List<FavorableTraitCost> allCosts = new ArrayList<>();
    allCosts.add(handleFavoredSingleTrait(trait, bonusPointCostFactor));
    return allCosts;
  }

  private FavorableTraitCost handleGeneralSingleTrait(Trait trait, int bonusPointCostFactor) {
    int freeTraitMax = Math.max(creationData.getMaximumFreeAbilityRank(), trait.getAbsoluteMinValue());
    int freePointsToAdd = Math.min(getCalculationBase(trait), freeTraitMax);
    int generalDotsSpent = 0;
    int bonusPointsSpent = 0;
    if (generalDotSum < getDefaultDotCount()) {
      int remainingGeneralPoints = getDefaultDotCount() - generalDotSum;
      generalDotsSpent = Math.min(remainingGeneralPoints, freePointsToAdd);

      increaseGeneralDotSum(generalDotsSpent);
      freePointsToAdd -= generalDotsSpent;
    }
    if (freePointsToAdd > 0) {
      bonusPointsSpent += freePointsToAdd * bonusPointCostFactor;
    }
    bonusPointsSpent += Math.max(getCalculationBase(trait) - freeTraitMax, 0) * bonusPointCostFactor;
    return new FavorableTraitCost(bonusPointsSpent, generalDotsSpent, 0);
  }

  private int getCalculationBase(Trait trait) {
    return getCreationCalculationValue(trait, creationData);
  }

  private Collection<FavorableTraitCost> handleGeneralTrait(Trait trait, final int bonusPointCostFactor) {
    List<FavorableTraitCost> allCosts = new ArrayList<>();
    allCosts.add(handleGeneralSingleTrait(trait, bonusPointCostFactor));
    return allCosts;
  }

  private void increasePicksForType(TraitStateType stateType) {
    statePicks.put(stateType, statePicks.get(stateType) + 1);
  }

  private void increaseGeneralDotSum(int generalDotsSpent) {
    if (generalDotsSpent == 0) {
      return;
    }
    generalDotSum += generalDotsSpent;
  }

  private Set<Trait> sortTraitsByStatus() {
    Set<Trait> orderedTraits = new LinkedHashSet<>();
    for (Trait trait : traits.getAll()) {
      if (!isCheapened(trait)) {
        addAllTraits(orderedTraits, trait);
      }
    }
    for (Trait trait : traits.getAll()) {
      if (!orderedTraits.contains(trait)) {
        addAllTraits(orderedTraits, trait);
      }
    }
    return orderedTraits;
  }

  private void addAllTraits(final Set<Trait> orderedTraits, Trait trait) {
    orderedTraits.add(trait);
  }

  private boolean isCheapened(Trait trait) {
    return traits.getState(trait).isCheapened();
  }
}