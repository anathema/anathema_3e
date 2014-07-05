package net.sf.anathema.platform.repositorytree;

import net.sf.anathema.library.message.Messaging;
import net.sf.anathema.library.resources.Resources;

import static net.sf.anathema.library.message.MessageType.Information;

public class AmountMessaging {

  private final Messaging messaging;
  private final String singleItem;
  private final String multiItem;

  public AmountMessaging(Messaging messaging, Resources resources) {
    this.messaging = messaging;
    this.singleItem = resources.getString("AnathemaCore.Tools.RepositoryView.Item");
    this.multiItem = resources.getString("AnathemaCore.Tools.RepositoryView.Items");
  }

  public void addMessage(String messagePattern, int amount) {
    String itemString;
    if (amount == 1) {
      itemString = singleItem;
    }
    else {
      itemString = multiItem;
    }
    messaging.addTemporaryMessage(Information, messagePattern, amount, itemString);
  }
}