package net.sf.anathema.hero.spiritual.model.traits;

import net.sf.anathema.characterengine.support.Announcer;
import net.sf.anathema.hero.traits.model.rules.minimum.DynamicMinimum;
import net.sf.anathema.library.event.ChangeListener;
import net.sf.anathema.points.model.xp.ExperiencePoints;
import net.sf.anathema.points.model.xp.ExperiencePointsListener;

public class ExperienceBasedMinimum implements DynamicMinimum {
  private final Announcer<ChangeListener> announcer = Announcer.to(ChangeListener.class);
  private int currentMinimum = 1;

  public ExperienceBasedMinimum(ExperiencePoints experiencePoints) {
    experiencePoints.addExperiencePointConfigurationListener(new ExperiencePointsListener() {
      @Override
      public void entriesAddedRemovedOrChanged() {
        int totalExperiencePoints = experiencePoints.getTotalExperiencePoints();
        if (totalExperiencePoints > 50){
          currentMinimum = 2;
          announcer.announce().changeOccurred();
        }
      }
    });
  }

  @Override
  public int getMinimum() {
    return currentMinimum;
  }

  @Override
  public void addChangedListener(ChangeListener listener) {
     announcer.addListener(listener);
  }
}
