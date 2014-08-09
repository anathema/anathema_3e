package net.sf.anathema.hero.abilities.advance.creation;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.sf.anathema.hero.abilities.model.AbilitiesModel;
import net.sf.anathema.hero.traits.advance.CurrentRatingCost;
import net.sf.anathema.hero.traits.display.Traits;
import net.sf.anathema.hero.traits.model.FavorableTraitCost;
import net.sf.anathema.hero.traits.model.Trait;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static net.sf.anathema.hero.traits.advance.TraitCalculationUtilities.getCreationCalculationValue;

public class AbilityCostCalculatorImpl implements AbilityCostCalculator {

  private AbilitiesModel abilitiesModel;
  private AbilityCreationData creationData;
  private final Multimap<Trait, FavorableTraitCost> costsByTrait = HashMultimap.create();
  private final Traits traits;
  private int favoredPicksSpent = 0;
  private int castePicksSpent = 0;
  private int supernalPicksSpent = 0;
  private int favoredDotSum = 0;
  private int generalDotSum = 0;

  public AbilityCostCalculatorImpl(AbilitiesModel abilitiesModel, AbilityCreationData creationData) {
    this.abilitiesModel = abilitiesModel;
    this.creationData = creationData;
    this.traits = abilitiesModel.getAll();
  }

  protected int getCostFactor(Trait trait) {
    CurrentRatingCost abilityCosts = creationData.getAbilityCosts(isCheapened(trait));
    return abilityCosts.getRatingCosts(getCalculationBase(trait));
  }

  public void recalculate() {
    clear();
    countFavoredPicks();
    Set<Trait> sortedTraits = sortTraitsByStatus();
    for (Trait trait : sortedTraits) {
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

  private void clear() {
    favoredPicksSpent = 0;
    castePicksSpent = 0;
    supernalPicksSpent = 0;
    favoredDotSum = 0;
    generalDotSum = 0;
    costsByTrait.clear();
  }

  private void countFavoredPicks() {
    for (Trait trait : traits) {
      switch (abilitiesModel.getState(trait).getType()) {
        case Favored:
          increaseFavoredPicksSpent();
          break;
        case Caste:
          increaseCastePicksSpent();
          break;
        case Supernal:
          increaseCastePicksSpent();
          increaseSupernalPicksSpent();
          break;
        default:
          break;
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

  private int getFavoredDotCount() {
    return creationData.getFavoredDotCount();
  }

  public int getFavoredPicksSpent() {
    return favoredPicksSpent;
  }

  public int getCastePicksSpent() {
    return castePicksSpent;
  }

  public int getSupernalPicksSpent() {
    return supernalPicksSpent;
  }

  public int getFreePointsSpent(boolean favored) {
    return favored ? favoredDotSum : generalDotSum;
  }

  protected Traits getTraits() {
    return traits;
  }

  private FavorableTraitCost handleFavoredSingleTrait(Trait trait, int bonusPointCostFactor) {
    int freeTraitMax = Math.max(creationData.getMaximumFreeAbilityRank(), trait.getAbsoluteMinValue());
    int freePointsToAdd = Math.min(getCalculationBase(trait), freeTraitMax);
    int favoredDotsSpent = 0;
    int generalDotsSpent = 0;
    int bonusPointsSpent = 0;
    if (getFreePointsSpent(true) < getFavoredDotCount()) {
      int remainingFavoredPoints = getFavoredDotCount() - getFreePointsSpent(true);
      favoredDotsSpent = Math.min(remainingFavoredPoints, freePointsToAdd);
      increaseFavoredDotSum(favoredDotsSpent);
      freePointsToAdd -= favoredDotsSpent;
    }
    if (freePointsToAdd > 0) {
      if (getFreePointsSpent(false) < getDefaultDotCount()) {
        int remainingGeneralPoints = getDefaultDotCount() - getFreePointsSpent(false);
        generalDotsSpent = Math.min(remainingGeneralPoints, freePointsToAdd);
        increaseGeneralDotSum(generalDotsSpent);
        freePointsToAdd -= generalDotsSpent;
      }
    }
    if (freePointsToAdd > 0) {
      if (getFreePointsSpent(false) < getDefaultDotCount()) {
        int remainingGeneralPoints = getDefaultDotCount() - getFreePointsSpent(false);
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
    if (getFreePointsSpent(false) < getDefaultDotCount()) {
      int remainingGeneralPoints = getDefaultDotCount() - getFreePointsSpent(false);
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

  private void increaseFavoredDotSum(int favoredDotsSpent) {
    favoredDotSum += favoredDotsSpent;
  }

  private void increaseFavoredPicksSpent() {
    favoredPicksSpent++;
  }

  private void increaseCastePicksSpent() {
    castePicksSpent++;
  }

  private void increaseSupernalPicksSpent() {
    supernalPicksSpent++;
  }

  private void increaseGeneralDotSum(int generalDotsSpent) {
    if (generalDotsSpent == 0) {
      return;
    }
    generalDotSum += generalDotsSpent;
  }

  private Set<Trait> sortTraitsByStatus() {
    Set<Trait> orderedTraits = new LinkedHashSet<>();
    for (Trait trait : traits) {
      if (!isCheapened(trait)) {
        addAllTraits(orderedTraits, trait);
      }
    }
    for (Trait trait : traits) {
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
    return abilitiesModel.getState(trait).isCheapened();
  }
}