package net.sf.anathema.hero.traits.model.rules.minimum;

import net.sf.anathema.library.event.ChangeListener;
import org.jmock.example.announcer.Announcer;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.max;

public class AggregatedDynamicMinimum implements DynamicMinimum {

  private final List<DynamicMinimum> minimums = new ArrayList<>();
  private Announcer<ChangeListener> announcer = new Announcer(ChangeListener.class);

  @Override
  public int getMinimum() {
    int overallMinimum = 0;
    for (DynamicMinimum minimum : minimums) {
      overallMinimum = max(overallMinimum, minimum.getMinimum());
    }
    return overallMinimum;
  }

  @Override
  public void addChangedListener(ChangeListener listener) {
    announcer.addListener(listener);
  }

  public void addMinimum(DynamicMinimum minimum) {
    minimums.add(minimum);
    announcer.announce().changeOccurred();
    minimum.addChangedListener(new ChangeListener() {
      @Override
      public void changeOccurred() {
        announcer.announce().changeOccurred();
      }
    });
  }
}
