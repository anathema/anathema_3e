package net.sf.anathema.framework.model;

import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.environment.Environment;
import net.sf.anathema.framework.extension.AnathemaExtension;
import net.sf.anathema.framework.messaging.CollectingMessaging;
import net.sf.anathema.framework.messaging.MessageContainer;
import net.sf.anathema.framework.messaging.Messaging;
import net.sf.anathema.framework.repository.FileSystemRepository;
import net.sf.anathema.framework.repository.Repository;
import net.sf.anathema.lib.registry.IRegistry;
import net.sf.anathema.lib.registry.Registry;

import java.io.File;

public class ApplicationModel implements IApplicationModel {

  private final IRegistry<String, AnathemaExtension> extensionRegistry = new Registry<>();
  private final FileSystemRepository repository;
  private final CollectingMessaging messaging;

  public ApplicationModel(File repositoryFolder, Environment environment) {
    this.repository = new FileSystemRepository(repositoryFolder);
    this.messaging = new CollectingMessaging(environment);
  }

  @Override
  public final Repository getRepository() {
    return repository;
  }

  @Override
  public final IRegistry<String, AnathemaExtension> getExtensionPointRegistry() {
    return extensionRegistry;
  }

  @Override
  public Messaging getMessaging() {
    return messaging;
  }

  @Override
  public MessageContainer getMessageContainer() {
    return messaging;
  }
}