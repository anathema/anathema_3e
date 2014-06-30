package net.sf.anathema.hero.charms.display.tree;

import net.sf.anathema.hero.charms.display.presenter.AbstractCharmGroupChangeListener;
import net.sf.anathema.hero.charms.display.presenter.CharmDisplayPropertiesMap;
import net.sf.anathema.hero.charms.display.presenter.CharmTreeArbitrator;
import net.sf.anathema.hero.charms.model.learn.LearningCharmTree;
import net.sf.anathema.lib.util.Identifier;

public class CharacterCharmGroupChangeListener extends AbstractCharmGroupChangeListener {

  public CharacterCharmGroupChangeListener(CharmTreeArbitrator arbitrator, CharmDisplayPropertiesMap displayPropertiesMap) {
    super(arbitrator, displayPropertiesMap);
  }

  @Override
  protected void modifyCharmVisuals(Identifier type) {
    // Nothing to do
  }

  @Override
  public LearningCharmTree getCurrentGroup() {
    return (LearningCharmTree) super.getCurrentGroup();
  }
}
