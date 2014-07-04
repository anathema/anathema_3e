package net.sf.anathema.platform.frame;

import net.sf.anathema.library.initialization.Registry;
import net.sf.anathema.library.initialization.RegistryImpl;
import net.sf.anathema.platform.environment.Environment;
import net.sf.anathema.platform.messaging.CollectingMessaging;
import net.sf.anathema.platform.messaging.MessageContainer;
import net.sf.anathema.platform.messaging.Messaging;
import net.sf.anathema.platform.repository.FileSystemRepository;
import net.sf.anathema.platform.repository.Repository;

import java.io.File;

public class ApplicationModelImpl implements ApplicationModel {

  private final Registry<String, AnathemaExtension> extensionRegistry = new RegistryImpl<>();
  private final FileSystemRepository repository;
  private final CollectingMessaging messaging;

  public ApplicationModelImpl(File repositoryFolder, Environment environment) {
    this.repository = new FileSystemRepository(repositoryFolder);
    this.messaging = new CollectingMessaging(environment);
  }

  @Override
  public final Repository getRepository() {
    return repository;
  }

  @Override
  public final Registry<String, AnathemaExtension> getExtensionRegistry() {
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