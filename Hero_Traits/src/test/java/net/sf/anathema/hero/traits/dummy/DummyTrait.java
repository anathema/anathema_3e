package net.sf.anathema.hero.traits.dummy;

import net.sf.anathema.hero.traits.model.Trait;
import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.library.event.IntegerChangedListener;
import net.sf.anathema.library.event.IntegerChangingListener;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class DummyTrait implements Trait {

  public static DummyTrait createLearnTrait(TraitType type, int creationValue, int experiencedValue) {
    DummyTrait trait = new DummyTrait(type);
    trait.setCreationValue(creationValue);
    trait.setExperiencedValue(experiencedValue);
    trait.setCurrentValue(experiencedValue);
    return trait;
  }

  private int currentValue;
  private final TraitType type;
  private int creationValue;
  private int experiencedValue;

  public DummyTrait(TraitType type) {
    this(type, 0);
  }

  public DummyTrait(TraitType type, int value) {
    this.type = type;
    this.currentValue = value;
  }

  @Override
  public void setCurrentValue(int value) {
    this.currentValue = value;
  }

  @Override
  public TraitType getType() {
    return type;
  }

  @Override
  public int getCurrentValue() {
    return currentValue;
  }

  @Override
  public int getMinimalValue() {
    return 0;
  }

  @Override
  public int getUnmodifiedMaximalValue() {
    return 10;
  }

  @Override
  public int getModifiedMaximalValue() {
    return 10;
  }

  @Override
  public void resetCurrentValue() {
    //not yet implemented
  }

  @Override
  public void addCurrentValueListener(IntegerChangedListener listener) {
    //not yet implemented
  }
  
  @Override
	public void addChangingValueListener(IntegerChangingListener listener) {
    //not yet implemented
	}

  @Override
  public void removeCurrentValueListener(IntegerChangedListener listener) {
    //not yet implemented
  }

	@Override
	public void removeChangingValueListener(IntegerChangingListener listener) {
	  //not yet implemented
	}

  @Override
  public int getMaximalValue() {
    return 0;
  }

  @Override
  public void resetCreationValue() {
    creationValue = 0;
  }

  @Override
  public void resetExperiencedValue() {
    experiencedValue = creationValue;
  }

  @Override
  public void setCreationValue(int value) {
    this.creationValue = value;
  }

  @Override
  public void setExperiencedValue(int value) {
    this.experiencedValue = value;
  }

  @Override
  public void setUncheckedCreationValue(int value) {
    //not yet implemented
  }

  @Override
  public void setUncheckedExperiencedValue(int value) {
    //not yet implemented
  }

  @Override
  public int getAbsoluteMinValue() {
    return 0;
  }

  @Override
  public int getCreationValue() {
    return creationValue;
  }

  @Override
  public int getExperiencedValue() {
    return experiencedValue;
  }

  @Override
  public boolean isLowerable() {
    return false;
  }

  @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
  @Override
  public boolean equals(Object o) {
    return EqualsBuilder.reflectionEquals(this, o);
  }

  @Override
  public int hashCode() {
    return HashCodeBuilder.reflectionHashCode(this);
  }
}