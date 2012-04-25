package net.sf.anathema.character.impl.model.context.trait;

import net.sf.anathema.character.generic.framework.additionaltemplate.model.IBasicTrait;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.IModifiableBasicTrait;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ITraitValueStrategy;
import net.sf.anathema.lib.control.intvalue.IntValueControl;

public class CreationTraitValueStrategy implements ITraitValueStrategy {

  @Override
  public int getCurrentValue(IBasicTrait trait) {
    return trait.getCreationValue();
  }

  @Override
  public int getMinimalValue(IBasicTrait trait) {
    return trait.getAbsoluteMinValue();
  }

  @Override
  public void setValue(IModifiableBasicTrait trait, int value) {
    trait.setCreationValue(value);
  }

  @Override
  public void notifyOnCreationValueChange(int value, IntValueControl creationValueControl) {
    creationValueControl.fireValueChangedEvent(value);
  }

  @Override
  public void notifyOnLearnedValueChange(int value, IntValueControl currentValueControl) {
    // throw new IllegalStateException("No changes on learn value should occur in character creation."); //$NON-NLS-1$
  }

  @Override
  public void resetCurrentValue(IModifiableBasicTrait trait) {
    trait.resetCreationValue();
  }

  @Override
  public int getCalculationValue(IModifiableBasicTrait trait) {
    return trait.getCreationCalculationValue();
  }
}