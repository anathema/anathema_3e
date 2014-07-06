package net.sf.anathema.hero.charms.model.history;

import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.hero.individual.change.ChangeMemento;

public class LearnedCharmMemento extends ChangeMemento {
  private Charm charm;

  public LearnedCharmMemento(Charm charm) {
    this.charm = charm;
  }
}
