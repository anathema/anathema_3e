package net.sf.anathema.platform.initialization.bootjobs;

import net.sf.anathema.platform.Version;
import net.sf.anathema.platform.environment.Environment;
import net.sf.anathema.platform.frame.ApplicationModel;
import net.sf.anathema.platform.item.ItemTypeCollection;
import net.sf.anathema.platform.repository.Repository;
import net.sf.anathema.platform.repositorytree.FileExporter;
import net.sf.anathema.platform.repositorytree.RepositoryZipPathCreator;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class RepositoryBackup {
  private final Environment environment;
  private final ApplicationModel model;

  public RepositoryBackup(Environment environment, ApplicationModel model) {
    this.environment = environment;
    this.model = model;
  }

  public void backupRepository() {
    try {
      Repository repository = model.getRepository();
      ItemTypeCollection itemTypeCollection = new ItemTypeCollection(environment);
      CleanupExportModel exportModel = new CleanupExportModel(itemTypeCollection, repository);
      if (exportModel.getPrintNameFilesInSelection().length == 0) {
        return;
      }
      RepositoryZipPathCreator creator = new RepositoryZipPathCreator(repository.getRepositoryPath());
      Path saveFile = getSaveFile();
      new FileExporter(creator, exportModel, environment).exportToZip(saveFile);
    } catch (IOException e) {
      throw new RuntimeException("Could not back up repository before launch. Please create a copy and delete all 1E characters manually.", e);
    }
  }

  private Path getSaveFile() {
    Repository repository = model.getRepository();
    String version = new Version(environment).asString();
    return Paths.get(repository.getRepositoryPath()).resolve("BackupForFirstLaunchOf" + version + ".zip");
  }
}