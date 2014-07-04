package net.sf.anathema.hero.charms.display.special;

import net.sf.anathema.framework.value.IntValueView;
import net.sf.anathema.library.event.IntegerChangedListener;
import org.jmock.example.announcer.Announcer;

public class ProxyIntValueView implements IntValueView {
  private final String labelText;
  private int maxValue;
  private final Announcer<IntegerChangedListener> valueListeners = new Announcer<>(IntegerChangedListener.class);
  private int value;
  private IntValueView actualView;

  public ProxyIntValueView(String labelText, int maxValue, int value) {
    this.labelText = labelText;
    this.maxValue = maxValue;
    this.value = value;
  }

  public void setActualView(IntValueView actualView) {
    this.actualView = actualView;
    actualView.addIntValueChangedListener(new IntegerChangedListener() {
      @Override
      public void valueChanged(int newValue) {
        valueListeners.announce().valueChanged(newValue);
      }
    });
  }

  @Override
  public void setValue(int newValue) {
    if (actualView != null) {
      actualView.setValue(newValue);
      return;
    }
    if (newValue > maxValue) {
      return;
    }
    this.value = newValue;
    valueListeners.announce().valueChanged(value);
  }

  @Override
  public void addIntValueChangedListener(IntegerChangedListener listener) {
    if (actualView != null) {
      actualView.addIntValueChangedListener(listener);
      return;
    }
    valueListeners.addListener(listener);
  }

  @Override
  public void removeIntValueChangedListener(IntegerChangedListener listener) {
    if (actualView != null) {
      actualView.removeIntValueChangedListener(listener);
      return;
    }
    valueListeners.removeListener(listener);
  }

  public int getMaxValue() {
    return maxValue;
  }

  public int getValue() {
    return value;
  }

  public String getLabel() {
    return labelText;
  }
}
