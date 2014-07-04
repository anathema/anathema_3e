package net.sf.anathema.hero.experience.model;

import net.sf.anathema.hero.individual.model.HeroModel;
import net.sf.anathema.library.event.ChangeListener;
import net.sf.anathema.library.identifier.Identifier;
import net.sf.anathema.library.identifier.SimpleIdentifier;

public interface ExperienceModel extends HeroModel {

  static final Identifier ID = new SimpleIdentifier("Experience");

  boolean isExperienced();

  void setExperienced(boolean experienced);

  void addStateChangeListener(ChangeListener listener);
}
