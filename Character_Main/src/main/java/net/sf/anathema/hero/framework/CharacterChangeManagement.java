package net.sf.anathema.hero.framework;

import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.library.event.ChangeListener;
import net.sf.anathema.platform.repository.ChangeManagement;
import org.jmock.example.announcer.Announcer;

public class CharacterChangeManagement implements ChangeManagement {

  private final Announcer<ChangeListener> control = Announcer.to(ChangeListener.class);
  private boolean dirty = false;
  private Hero hero;

  public CharacterChangeManagement(Hero hero) {
    this.hero = hero;
  }

  @Override
  public boolean isDirty() {
    return dirty;
  }

  @Override
  public void addDirtyListener(ChangeListener changeListener) {
    control.addListener(changeListener);
  }

  private void setDirty() {
    this.dirty = true;
    control.announce().changeOccurred();
  }

  @Override
  public void setClean() {
    this.dirty = false;
    control.announce().changeOccurred();
  }

  @Override
  public void removeDirtyListener(ChangeListener changeListener) {
    control.removeListener(changeListener);
  }

  public void initListening() {
    hero.getChangeAnnouncer().addListener(flavor -> setDirty());
  }
}