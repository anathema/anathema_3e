package net.sf.anathema.hero.display.fx.perspective;

import net.sf.anathema.hero.application.perspective.model.CharacterIdentifier;
import net.sf.anathema.platform.messaging.CategorizedMessaging;
import net.sf.anathema.platform.messaging.MessageCategory;

import static net.sf.anathema.platform.messaging.CategorizedMessaging.Default_Message_Category;

public class CharacterMessaging {
  private CategorizedMessaging messaging;
  private MessageCategory activeCharacterCategory = Default_Message_Category;

  public void showMessagesFor(CharacterIdentifier identifier) {
    this.activeCharacterCategory = new MessageCategory(identifier.getId());
    messaging.activateCategory(activeCharacterCategory);
  }


  public void setDelegate(CategorizedMessaging messaging) {
    this.messaging = messaging;
  }

  public MessageCategory getActiveCategory() {
    return activeCharacterCategory;
  }
}
