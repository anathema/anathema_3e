package net.sf.anathema.platform.frame;

import net.sf.anathema.library.initialization.Registry;
import net.sf.anathema.platform.messaging.MessageContainer;
import net.sf.anathema.platform.messaging.Messaging;
import net.sf.anathema.platform.repository.Repository;

public interface ApplicationModel {

  Repository getRepository();

  Registry<String, AnathemaExtension> getExtensionRegistry();

  Messaging getMessaging();

  MessageContainer getMessageContainer();
}