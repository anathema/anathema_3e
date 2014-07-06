package net.sf.anathema.hero.traits.model.rules;

import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.traits.model.TraitLimitation;
import net.sf.anathema.hero.traits.model.TraitModelFetcher;
import net.sf.anathema.hero.traits.model.TraitRules;
import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.hero.traits.model.rules.minimum.DynamicMinimum;
import net.sf.anathema.hero.traits.template.LimitationTemplate;
import net.sf.anathema.hero.traits.template.TraitTemplate;
import net.sf.anathema.library.number.Range;

import static java.lang.Math.max;

public class TraitRulesImpl implements TraitRules {

  private final DynamicMinimum dynamicMinimum;
  private int capModifier = 0;
  private final TraitTemplate template;
  private final TraitType traitType;
  private Hero hero;

  public TraitRulesImpl(TraitType traitType, TraitTemplate template, Hero hero) {
    this.traitType = traitType;
    this.hero = hero;
    this.dynamicMinimum = TraitModelFetcher.fetch(hero).getMinimumMap().getMinimum(traitType);
    this.template = template;
  }

  @Override
  public int getAbsoluteMaximumValue() {
    return getLimitation().getAbsoluteLimit(hero);
  }

  public TraitLimitation getLimitation() {
    LimitationTemplate limitation = template.limitation;
    return TraitModelFetcher.fetch(hero).createLimitation(limitation);
  }

  private int getCreationMaximumValue() {
    return getCurrentMaximumValue(true);
  }

  @Override
  public int getCurrentMaximumValue(boolean modified) {
    return getLimitation().getCurrentMaximum(hero, modified) + (modified ? capModifier : 0);
  }

  @Override
  public int getAbsoluteMinimumValue() {
    return template.minimumValue;
  }

  @Override
  public int getCurrentMinimumValue() {
    return max(getAbsoluteMinimumValue(), dynamicMinimum.getMinimum());
  }

  @Override
  public int getStartValue() {
    return template.startValue;
  }

  @Override
  public void setCapModifier(int modifier) {
    capModifier = modifier;
  }

  @Override
  public boolean isReducible() {
    return template.modificationType == ModificationType.Free;
  }

  @Override
  public TraitType getType() {
    return traitType;
  }

  @Override
  public int getExperiencedValue(int creationValue, int demandedValue) {
    Range range;
    int maximumValue = getCurrentMaximumValue(true);
    if (isReducible()) {
      range = Range.bounded(getAbsoluteMinimumValue(), maximumValue);
    } else {
      boolean isImmutable = template.modificationType == ModificationType.Immutable;
      range = Range.bounded(max(Math.min(creationValue, maximumValue), getAbsoluteMinimumValue()),
        isImmutable ? creationValue : maximumValue);
    }
    return getCorrectedValue(demandedValue, range);
  }

  @Override
  public int getCreationValue(int demandedValue) {
    Range currentCreationPointRange = Range.bounded(getCurrentMinimumValue(), getCreationMaximumValue());
    return getCorrectedValue(demandedValue, currentCreationPointRange);
  }

  private int getCorrectedValue(int value, Range allowedRange) {
    if (allowedRange.contains(value)) {
      return value;
    }
    if (value < allowedRange.getLowerBound()) {
      return allowedRange.getLowerBound();
    }
    return allowedRange.getUpperBound();
  }

  @Override
  public boolean isRequiredFavored() {
    return false;
  }
}