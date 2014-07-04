package net.sf.anathema.platform.initialization.bootjobs;

import net.sf.anathema.ProxySplashscreen;
import net.sf.anathema.library.initialization.Weight;
import net.sf.anathema.library.logging.Logger;
import net.sf.anathema.library.resources.Resources;
import net.sf.anathema.platform.Version;
import net.sf.anathema.platform.environment.Environment;
import net.sf.anathema.platform.frame.ApplicationModel;
import net.sf.anathema.platform.initialization.BootJob;
import net.sf.anathema.platform.initialization.RegisteredBootJob;

import java.io.File;
import java.text.MessageFormat;

import static java.text.MessageFormat.format;

@RegisteredBootJob
@Weight(weight = 5)
public class RepositoryUpdate implements BootJob {
  private static final Logger logger = Logger.getLogger(RepositoryUpdate.class);

  @Override
  public void run(Environment environment, ApplicationModel model) {
    if (!repositoryAlreadyExists(model)) {
      createRepositoryAtVersion(environment, model);
      return;
    }
    ProxySplashscreen.getInstance().displayStatusMessage(environment.getString("Bootjob.UpdateRepository"));
    Version anathemaVersion = new Version(environment);
    RepositoryVersion repositoryVersion = new RepositoryVersion(model.getRepository());
    logger.info(format("Found repository at version {0}.", repositoryVersion.asString()));
    if (!repositoryVersion.needsUpdateTo(anathemaVersion)) {
      logger.info(format("No update necessary."));
      return;
    }
    logger.info(format("Updating to {0}.", anathemaVersion.asString()));
    repositoryVersion.updateTo(anathemaVersion);
  }

  private void createRepositoryAtVersion(Resources resources, ApplicationModel model) {
    Version anathemaVersion = new Version(resources);
    RepositoryVersion repositoryVersion = new RepositoryVersion(model.getRepository());
    logger.info(MessageFormat.format("No repository found. Creating repository for version {0}.", anathemaVersion.asString()));
    repositoryVersion.updateTo(anathemaVersion);
  }

  private boolean repositoryAlreadyExists(ApplicationModel model) {
    return new File(model.getRepository().getRepositoryPath()).exists();
  }
}