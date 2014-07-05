package net.sf.anathema.hero.traits.model;

import com.google.common.base.Preconditions;
import net.sf.anathema.hero.concept.model.concept.CasteType;
import net.sf.anathema.hero.concept.model.concept.ConceptChange;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.traits.model.state.MappableTypeIncrementChecker;
import net.sf.anathema.hero.traits.model.state.NullTraitStateModel;
import net.sf.anathema.hero.traits.model.state.TraitState;
import net.sf.anathema.hero.traits.model.state.TraitStateModel;
import net.sf.anathema.hero.traits.model.state.TraitStateModelImpl;
import net.sf.anathema.library.change.ChangeFlavor;
import net.sf.anathema.library.change.FlavoredChangeListener;
import net.sf.anathema.library.event.IntegerChangedListener;
import net.sf.anathema.library.number.Range;
import org.jmock.example.announcer.Announcer;

public class DefaultTrait implements Trait {

  private int capModifier = 0;
  private int creationValue;
  private int experiencedValue = TraitRules.UNEXPERIENCED;
  private final ValueChangeChecker checker;
  private TraitStateModel traitFavorization;
  private final TraitRules traitRules;
  private final Announcer<IntegerChangedListener> creationPointControl = Announcer.to(IntegerChangedListener.class);
  private final Announcer<IntegerChangedListener> currentValueControl = Announcer.to(IntegerChangedListener.class);
  private final TraitValueStrategy valueStrategy;

  public DefaultTrait(Hero hero, TraitRules traitRules, CasteType[] castes, ValueChangeChecker valueChangeChecker,
                      MappableTypeIncrementChecker<TraitState> favoredIncrementChecker) {
    this(hero, traitRules, valueChangeChecker);
    this.traitFavorization = new TraitStateModelImpl(hero, castes, favoredIncrementChecker, this, traitRules.isRequiredFavored());
    hero.getChangeAnnouncer().addListener(new ResetCurrentValueOnCasteChange());
  }

  public DefaultTrait(Hero hero, TraitRules traitRules, ValueChangeChecker checker) {
    Preconditions.checkNotNull(traitRules);
    this.traitRules = traitRules;
    TraitModel traits = TraitModelFetcher.fetch(hero);
    this.valueStrategy = traits.getValueStrategy();
    this.traitFavorization = new NullTraitStateModel();
    this.checker = checker;
    this.creationValue = traitRules.getStartValue();
  }

  @Override
  public final int getCreationValue() {
    return creationValue;
  }

  @Override
  public TraitStateModel getFavorization() {
    return traitFavorization;
  }

  @Override
  public void setCreationValue(int value) {
    value = Math.max(value, traitFavorization.getMinimalValue());
    int correctedValue = traitRules.getCreationValue(value);
    if (this.creationValue == correctedValue) {
      return;
    }
    this.creationValue = correctedValue;
    creationPointControl.announce().valueChanged(this.creationValue);
    valueStrategy.notifyOnCreationValueChange(getCurrentValue(), currentValueControl);
  }

  @Override
  public void setUncheckedCreationValue(int value) {
    if (this.creationValue == value) {
      return;
    }
    this.creationValue = value;
    creationPointControl.announce().valueChanged(this.creationValue);
    valueStrategy.notifyOnCreationValueChange(getCurrentValue(), currentValueControl);
  }

  @Override
  public final void resetCreationValue() {
    setCreationValue(getCreationValue());
  }

  @Override
  public final void resetExperiencedValue() {
    if (getExperiencedValue() != TraitRules.UNEXPERIENCED) {
      setExperiencedValue(Math.max(getCreationValue(), getExperiencedValue()));
    }
  }

  @Override
  public String toString() {
    return getType() + ":" + getCreationValue();
  }

  @Override
  public int getCurrentValue() {
    return valueStrategy.getCurrentValue(this);
  }

  @Override
  public void setCurrentValue(int value) {
    if (!checker.isValidNewValue(value)) {
      resetCurrentValue();
    } else {
      if (value == getCurrentValue()) {
        return;
      }
      valueStrategy.setValue(this, value);
    }
  }

  @Override
  public final int getExperiencedValue() {
    return experiencedValue;
  }

  @Override
  public final void setExperiencedValue(int value) {
    int correctedValue = traitRules.getExperiencedValue(getCreationValue(), value);
    if (correctedValue == experiencedValue) {
      return;
    }
    this.experiencedValue = correctedValue;
    valueStrategy.notifyOnLearnedValueChange(getCurrentValue(), currentValueControl);
  }

  @Override
  public final void setUncheckedExperiencedValue(int value) {
    if (value == experiencedValue) {
      return;
    }
    this.experiencedValue = value;
    valueStrategy.notifyOnLearnedValueChange(getCurrentValue(), currentValueControl);
  }

  @Override
  public final void resetCurrentValue() {
    valueStrategy.resetCurrentValue(this);
  }

  public void applyCapModifier(int modifier) {
    capModifier += modifier;
    traitRules.setCapModifier(capModifier);
  }

  @Override
  public int getUnmodifiedMaximalValue() {
    return traitRules.getCurrentMaximumValue(false);
  }

  @Override
  public int getModifiedMaximalValue() {
    return traitRules.getCurrentMaximumValue(true);
  }

  @Override
  public void setModifiedCreationRange(int lowerBound, int upperBound) {
    traitRules.setModifiedCreationRange(Range.bounded(lowerBound, upperBound));
    resetCreationValue();
  }

  @Override
  public final int getMinimalValue() {
    return valueStrategy.getMinimalValue(this);
  }

  @Override
  public boolean isCasteOrFavored() {
    return getFavorization().isCasteOrFavored();
  }

  @Override
  public final boolean isLowerable() {
    return traitRules.isReducible();
  }

  @Override
  public int getAbsoluteMinValue() {
    return traitRules.getAbsoluteMinimumValue();
  }

  @Override
  public final TraitType getType() {
    return traitRules.getType();
  }

  @Override
  public final int getMaximalValue() {
    return traitRules.getAbsoluteMaximumValue();
  }

  @Override
  public final void addCreationPointListener(IntegerChangedListener listener) {
    creationPointControl.addListener(listener);
  }

  @Override
  public final void removeCreationPointListener(IntegerChangedListener listener) {
    creationPointControl.removeListener(listener);
  }

  @Override
  public final void addCurrentValueListener(IntegerChangedListener listener) {
    currentValueControl.addListener(listener);
  }

  @Override
  public int hashCode() {
    return getType().getId().hashCode();
  }

  public class ResetCurrentValueOnCasteChange implements FlavoredChangeListener {

    @Override
    public void changeOccurred(ChangeFlavor flavor) {
      if (flavor == ConceptChange.FLAVOR_CASTE) {
        resetCurrentValue();
      }
    }
  }
}