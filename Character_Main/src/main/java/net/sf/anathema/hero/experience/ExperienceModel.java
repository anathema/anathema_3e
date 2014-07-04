package net.sf.anathema.hero.experience;

import net.sf.anathema.hero.model.HeroModel;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.SimpleIdentifier;
import net.sf.anathema.library.event.ChangeListener;

public interface ExperienceModel extends HeroModel {

  static final Identifier ID = new SimpleIdentifier("Experience");

  boolean isExperienced();

  void setExperienced(boolean experienced);

  void addStateChangeListener(ChangeListener listener);
}
