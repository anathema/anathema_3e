package net.sf.anathema.hero.traits.model;

import com.google.common.base.Preconditions;
import net.sf.anathema.hero.concept.model.concept.CasteType;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.traits.model.state.MappableTypeIncrementChecker;
import net.sf.anathema.hero.traits.model.state.NullTraitState;
import net.sf.anathema.hero.traits.model.state.TraitState;
import net.sf.anathema.hero.traits.model.state.TraitStateImpl;
import net.sf.anathema.hero.traits.model.state.TraitStateType;
import net.sf.anathema.library.event.IntegerChangedListener;
import org.jmock.example.announcer.Announcer;

public class TraitImpl implements Trait {

  private int capModifier = 0;
  private int creationValue;
  private int experiencedValue = TraitRules.UNEXPERIENCED;
  private final ValueChangeChecker checker;
  private TraitState stateModel;
  private final TraitRules traitRules;
  private final Announcer<IntegerChangedListener> creationPointControl = Announcer.to(IntegerChangedListener.class);
  private final Announcer<IntegerChangedListener> currentValueControl = Announcer.to(IntegerChangedListener.class);
  private final TraitValueStrategy valueStrategy;

  public TraitImpl(Hero hero, TraitRules traitRules, CasteType[] castes, ValueChangeChecker valueChangeChecker,
                   MappableTypeIncrementChecker<TraitStateType> favoredIncrementChecker) {
    this(hero, traitRules, valueChangeChecker);
    this.stateModel = new TraitStateImpl(hero, castes, favoredIncrementChecker, this, traitRules.isRequiredFavored());
    traitRules.addChangeListener(this::resetCurrentValue);
  }

  public TraitImpl(Hero hero, TraitRules traitRules, ValueChangeChecker checker) {
    Preconditions.checkNotNull(traitRules);
    this.traitRules = traitRules;
    TraitModel traits = TraitModelFetcher.fetch(hero);
    this.valueStrategy = traits.getValueStrategy();
    this.stateModel = new NullTraitState();
    this.checker = checker;
    this.creationValue = traitRules.getStartValue();
  }

  @Override
  public final int getCreationValue() {
    return creationValue;
  }

  public TraitState getStateModel() {
    return stateModel;
  }

  @Override
  public void setCreationValue(int value) {
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
  public void resetCurrentValue() {
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
  public int getMinimalValue() {
    return valueStrategy.getMinimalValue(this);
  }

  @Override
  public boolean isCasteOrFavored() {
    return getStateModel().isCasteOrFavored();
  }

  @Override
  public boolean isLowerable() {
    return traitRules.isReducible();
  }

  @Override
  public int getAbsoluteMinValue() {
    return traitRules.getAbsoluteMinimumValue();
  }

  @Override
  public TraitType getType() {
    return traitRules.getType();
  }

  @Override
  public int getMaximalValue() {
    return traitRules.getAbsoluteMaximumValue();
  }

  @Override
  public void addCurrentValueListener(IntegerChangedListener listener) {
    currentValueControl.addListener(listener);
  }

  @Override
  public void removeCurrentValueListener(IntegerChangedListener listener) {
    currentValueControl.removeListener(listener);
  }

  @Override
  public int hashCode() {
    return getType().getId().hashCode();
  }
}