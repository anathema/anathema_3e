package net.sf.anathema.hero.traits.model.context;

import net.sf.anathema.hero.traits.model.Trait;
import net.sf.anathema.hero.traits.model.TraitValueStrategy;
import net.sf.anathema.library.event.IntegerChangedListener;
import net.sf.anathema.library.event.IntegerChangingListener;

import org.jmock.example.announcer.Announcer;

public class ProxyTraitValueStrategy implements TraitValueStrategy {

  private TraitValueStrategy strategy;

  public ProxyTraitValueStrategy(TraitValueStrategy strategy) {
    this.strategy = strategy;
  }

  @Override
  public int getCurrentValue(Trait trait) {
    return strategy.getCurrentValue(trait);
  }

  @Override
  public int getMinimalValue(Trait trait) {
    return strategy.getMinimalValue(trait);
  }

  @Override
  public void setValue(Trait trait, int value) {
    strategy.setValue(trait, value);
  }

  @Override
  public void notifyOnCreationValueChange(int value, Announcer<IntegerChangedListener> currentValueControl) {
    strategy.notifyOnCreationValueChange(value, currentValueControl);
  }
  
  @Override
	public void notifyOnCreationValueChanging(int from, int to, Announcer<IntegerChangingListener> changingValueControl) {
  	strategy.notifyOnCreationValueChanging(from, to, changingValueControl);
	}

  public void setStrategy(TraitValueStrategy strategy) {
    this.strategy = strategy;
  }

  @Override
  public void notifyOnLearnedValueChange(int value, Announcer<IntegerChangedListener> currentValueControl) {
    strategy.notifyOnLearnedValueChange(value, currentValueControl);
  }
  
  @Override
	public void notifyOnLearnedValueChanging(int from, int to, Announcer<IntegerChangingListener> changingValueControl) {
		strategy.notifyOnLearnedValueChanging(from, to, changingValueControl);
	}

  @Override
  public void resetCurrentValue(Trait trait) {
    strategy.resetCurrentValue(trait);
  }
}