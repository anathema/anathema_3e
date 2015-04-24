package net.sf.anathema.hero.traits.model;

import net.sf.anathema.library.event.IntegerChangedListener;

public class NullTrait implements Trait {
  @Override
  public TraitType getType() {
    return new TraitType("Missing Trait");
  }

  @Override
  public int getCurrentValue() {
    return 0;
  }

  @Override
  public int getCreationValue() {
    return 0;
  }

  @Override
  public int getExperiencedValue() {
    return 0;
  }

  @Override
  public int getAbsoluteMinValue() {
    return 0;
  }

  @Override
  public boolean isLowerable() {
    return false;
  }

  @Override
  public void resetCreationValue() {
    //nothing to do
  }

  @Override
  public void resetExperiencedValue() {
    //nothing to do
  }

  @Override
  public void setCreationValue(int value) {
    //nothing to do
  }

  @Override
  public void setExperiencedValue(int value) {
    //nothing to do
  }

  @Override
  public int getMaximalValue() {
    return 0;
  }

  @Override
  public void addCurrentValueListener(IntegerChangedListener listener) {
    //nothing to do
  }

  @Override
  public void removeCurrentValueListener(IntegerChangedListener listener) {
    //nothing to do
  }

  @Override
  public int getModifiedMaximalValue() {
    return 0;
  }

  @Override
  public int getUnmodifiedMaximalValue() {
    return 0;
  }

  @Override
  public void setUncheckedCreationValue(int value) {
    //nothing to do
  }

  @Override
  public void setUncheckedExperiencedValue(int value) {
    //nothing to do
  }

  @Override
  public void setCurrentValue(int value) {
    //nothing to do
  }

  @Override
  public int getMinimalValue() {
    return 0;
  }

  @Override
  public void resetCurrentValue() {
    //nothing to do
  }
}