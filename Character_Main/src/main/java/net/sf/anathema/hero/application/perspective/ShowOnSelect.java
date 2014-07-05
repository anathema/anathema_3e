package net.sf.anathema.hero.application.perspective;

import net.sf.anathema.hero.application.perspective.model.CharacterIdentifier;
import net.sf.anathema.platform.messaging.CategorizedMessaging;
import net.sf.anathema.platform.messaging.MessageCategory;

public class ShowOnSelect implements Selector<CharacterIdentifier> {
  private CategorizedMessaging messaging;
  private final CharacterStackPresenter characterStack;

  public ShowOnSelect(CategorizedMessaging messaging, CharacterStackPresenter characterStack) {
    this.messaging = messaging;
    this.characterStack = characterStack;
  }

  @Override
  public void selected(CharacterIdentifier identifier) {
    messaging.activateCategory(new MessageCategory(identifier.getId()));
    characterStack.showCharacter(identifier);
  }
}
