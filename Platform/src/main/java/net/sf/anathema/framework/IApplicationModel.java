package net.sf.anathema.framework;

import net.sf.anathema.framework.extension.AnathemaExtension;
import net.sf.anathema.framework.messaging.MessageContainer;
import net.sf.anathema.framework.messaging.Messaging;
import net.sf.anathema.framework.repository.Repository;
import net.sf.anathema.library.initialization.Registry;

public interface IApplicationModel {

  Repository getRepository();

  Registry<String, AnathemaExtension> getExtensionPointRegistry();

  Messaging getMessaging();

  MessageContainer getMessageContainer();
}