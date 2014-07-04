package net.sf.anathema.hero.experience.model;

import net.sf.anathema.hero.environment.HeroEnvironment;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.individual.model.HeroModel;
import net.sf.anathema.library.change.AnnounceChangeListener;
import net.sf.anathema.library.change.ChangeAnnouncer;
import net.sf.anathema.library.event.ChangeListener;
import net.sf.anathema.library.identifier.Identifier;
import org.jmock.example.announcer.Announcer;

public class ExperienceModelImpl implements ExperienceModel, HeroModel {
  private final Announcer<ChangeListener> stateAnnouncer = new Announcer<>(ChangeListener.class);
  private boolean experienced = false;

  @Override
  public Identifier getId() {
    return ID;
  }

  @Override
  public void initialize(HeroEnvironment environment, Hero hero) {
    // nothing to do
  }

  @Override
  public void initializeListening(ChangeAnnouncer announcer) {
    stateAnnouncer.addListener(new AnnounceChangeListener(announcer, ExperienceChange.FLAVOR_EXPERIENCE_STATE));
   }

  // todo (sandra): redirect to ChangeAnnouncer
  public void addStateChangeListener(ChangeListener listener) {
    stateAnnouncer.addListener(listener);
  }

  @Override
  public boolean isExperienced() {
    return experienced;
  }

  @Override
  public void setExperienced(boolean experienced) {
    if (this.experienced) {
      return;
    }
    this.experienced = experienced;
    stateAnnouncer.announce().changeOccurred();
  }
}
