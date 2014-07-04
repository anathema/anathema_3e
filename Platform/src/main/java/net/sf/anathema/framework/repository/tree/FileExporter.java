package net.sf.anathema.framework.repository.tree;

import net.sf.anathema.framework.repository.access.RepositoryFileAccess;
import net.sf.anathema.framework.view.PrintNameFile;
import net.sf.anathema.lib.io.InputOutput;
import net.sf.anathema.library.resources.Resources;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class FileExporter {
  private RepositoryZipPathCreator creator;
  private ExportModel model;
  private Resources resources;

  public FileExporter(RepositoryZipPathCreator repositoryZipPathCreator, ExportModel model, Resources resources) {
    this.creator = repositoryZipPathCreator;
    this.model = model;
    this.resources = resources;
  }

  public PrintNameFile[] exportToZip(Path saveFile) throws IOException {
    PrintNameFile[] printNameFiles = model.getPrintNameFilesInSelection();
    try (ZipOutputStream zipOutputStream = new ZipOutputStream(Files.newOutputStream(saveFile))) {
      zipOutputStream.setComment(resources.getString("Anathema.Version.Numeric"));
      for (PrintNameFile printNameFile : printNameFiles) {
        RepositoryFileAccess access = model.getFileAccess(printNameFile);
        for (File file : access.getFiles()) {
          ZipEntry entry = createZipEntry(file, printNameFile);
          try (InputStream inputStream = access.openInputStream(file)) {
            zipOutputStream.putNextEntry(entry);
            InputOutput.copy(inputStream, zipOutputStream);
            zipOutputStream.closeEntry();
          }
        }
      }
    }
    return printNameFiles;
  }

  ZipEntry createZipEntry(File file, PrintNameFile printNameFile) {
    ZipEntry entry = new ZipEntry(creator.createZipPath(file));
    entry.setComment(resources.getString(
            "Anathema.Version.Numeric") + "#" + printNameFile.getItemType() + "#" + printNameFile.getRepositoryId());
    return entry;
  }
}