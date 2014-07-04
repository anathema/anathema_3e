package net.sf.anathema.platform.initialization.bootjobs;

import net.sf.anathema.library.initialization.Weight;
import net.sf.anathema.platform.environment.Environment;
import net.sf.anathema.platform.frame.ApplicationModel;
import net.sf.anathema.platform.initialization.BootJob;
import net.sf.anathema.platform.initialization.RegisteredBootJob;

@RegisteredBootJob
@Weight(weight = 0)
//First back up the repo so all the other jobs can't do any harm
public class RepositoryBackupBootJob implements BootJob {
  @Override
  public void run(Environment environment, ApplicationModel model) {
    new RepositoryBackup(environment, model).backupRepository();
  }
}
