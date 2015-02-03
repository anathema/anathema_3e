package net.sf.anathema.hero.display.fx.perspective.navigation;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import net.sf.anathema.hero.application.perspective.Selector;
import net.sf.anathema.hero.application.perspective.model.HeroIdentifier;

public class HeroSelected implements EventHandler<ActionEvent> {
  private final Selector<HeroIdentifier> characterSelector;
  private final HeroIdentifier identifier;

  public HeroSelected(Selector<HeroIdentifier> characterSelector, HeroIdentifier identifier) {
    this.characterSelector = characterSelector;
    this.identifier = identifier;
  }

  @Override
  public void handle(ActionEvent actionEvent) {
    characterSelector.selected(identifier);
  }
}
