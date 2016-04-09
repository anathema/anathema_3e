package net.sf.anathema.hero.traits.model.context;

import net.sf.anathema.hero.traits.model.Trait;
import net.sf.anathema.hero.traits.model.TraitRules;
import net.sf.anathema.hero.traits.model.TraitValueStrategy;
import net.sf.anathema.library.event.IntegerChangedListener;
import net.sf.anathema.library.event.IntegerChangingListener;

import org.jmock.example.announcer.Announcer;

public class ExperiencedTraitValueStrategy implements TraitValueStrategy {

  @Override
  public int getMinimalValue(Trait trait) {
    return trait.isLowerable() ? trait.getAbsoluteMinValue() : trait.getCreationValue();
  }

  @Override
  public int getCurrentValue(Trait trait) {
    if (trait.getExperiencedValue() == TraitRules.UNEXPERIENCED) {
      return trait.getCreationValue();
    }
    return trait.getExperiencedValue();
  }

  @Override
  public void setValue(Trait trait, int value) {
    trait.setExperiencedValue(value);
  }

  @Override
  public void notifyOnCreationValueChange(int value, Announcer<IntegerChangedListener> currentValueControl) {
    // throw new IllegalStateException("No changes on creation value should occur in experienced mode.");
  }
  
  @Override
	public void notifyOnCreationValueChanging(int from, int to, Announcer<IntegerChangingListener> changingValueControl) {
   // throw new IllegalStateException("No changes on creation value should occur in experienced mode.");
	}

  @Override
  public void notifyOnLearnedValueChange(int value, Announcer<IntegerChangedListener> currentValueControl) {
    currentValueControl.announce().valueChanged(value);
  }

  @Override
	public void notifyOnLearnedValueChanging(int from, int to, Announcer<IntegerChangingListener> changingValueControl) {
		changingValueControl.announce().valueChanged(from, to);
	}
  
  @Override
  public void resetCurrentValue(Trait trait) {
    trait.resetExperiencedValue();
  }
}