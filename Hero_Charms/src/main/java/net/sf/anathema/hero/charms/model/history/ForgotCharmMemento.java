package net.sf.anathema.hero.charms.model.history;

import net.sf.anathema.magic.data.Charm;
import net.sf.anathema.hero.individual.change.ChangeMemento;

public class ForgotCharmMemento extends ChangeMemento {
  private Charm charm;

  public ForgotCharmMemento(Charm charm) {
    this.charm = charm;
  }
}
