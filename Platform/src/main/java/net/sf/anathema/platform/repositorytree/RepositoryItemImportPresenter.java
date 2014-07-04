package net.sf.anathema.platform.repositorytree;

import net.sf.anathema.library.collection.MultiEntryMap;
import net.sf.anathema.library.exception.PersistenceException;
import net.sf.anathema.library.interaction.model.Tool;
import net.sf.anathema.library.io.FileExtension;
import net.sf.anathema.library.io.SingleFileChooser;
import net.sf.anathema.platform.environment.Environment;
import net.sf.anathema.platform.item.IItemType;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

public class RepositoryItemImportPresenter {

  private final SingleFileChooser fileChooser;
  private final IRepositoryTreeModel model;
  private final IRepositoryTreeView view;
  private final RepositoryZipPathCreator creator;
  private final AmountMessaging messaging;
  private final Environment environment;

  public RepositoryItemImportPresenter(
          Environment environment, SingleFileChooser fileChooser,
          IRepositoryTreeModel repositoryTreeModel,
          IRepositoryTreeView treeView,
          AmountMessaging fileCountMessaging) {
    this.environment = environment;
    this.fileChooser = fileChooser;
    this.model = repositoryTreeModel;
    this.view = treeView;
    this.messaging = fileCountMessaging;
    this.creator = new RepositoryZipPathCreator(model.getRepositoryPath());
  }

  public void initPresentation() {
    Tool tool = view.addTool();
    tool.setTooltip(environment.getString("AnathemaCore.Tools.RepositoryView.ImportToolTip"));
    tool.setText(environment.getString("AnathemaCore.Tools.RepositoryView.ImportName"));
    tool.setIcon(new FileUi().getImportFilePath());
    tool.setCommand(() -> {
      try {
        String description = environment.getString("Filetype.zip");
        Path loadFile = fileChooser.selectLoadFile(new FileExtension(description, "*.zip"));
        if (loadFile == null) {
          return;
        }
        try (ZipFile importZipFile = new ZipFile(loadFile.toFile())) {
          MultiEntryMap<String, ZipEntry> entriesByItem = groupEntriesByItems(importZipFile);
          for (String comment : entriesByItem.keySet()) {
            String[] splitComment = comment.split("#", 3);
            if (!splitComment[0].equals(environment.getString("Anathema.Version.Numeric"))) {
              continue;
            }
            IItemType type = model.getItemTypeForId(splitComment[1]);
            String id = splitComment[2];
            String mainFilePath = creator.createZipPath(model.getMainFilePath(type, id));
            RepositoryImportHandler handler = new RepositoryImportHandler(model, type, id);
            for (ZipEntry entry : entriesByItem.get(comment)) {
              try (InputStream inputStream = importZipFile.getInputStream(entry)) {
                handler.importStream(mainFilePath, inputStream, entry.getName());
              }
            }
            model.refreshItem(type, handler.getNewId());
          }
          messaging.addMessage("AnathemaCore.Tools.RepositoryView.ImportDoneMessage", entriesByItem.keySet().size());
        }
      } catch (ZipException e) {
        environment.handle(e, environment.getString("AnathemaCore.Tools.RepositoryView.NoZipFileError"));
      } catch (IOException e) {
        environment.handle(e, environment.getString("AnathemaCore.Tools.RepositoryView.FileError"));
      } catch (PersistenceException e) {
        environment.handle(e, environment.getString("AnathemaCore.Tools.RepositoryView.RepositoryError"));
      }
    });
  }

  private MultiEntryMap<String, ZipEntry> groupEntriesByItems(ZipFile importZipFile) {
    Enumeration<? extends ZipEntry> entries = importZipFile.entries();
    MultiEntryMap<String, ZipEntry> entriesByComment = new MultiEntryMap<>();
    for (; entries.hasMoreElements(); ) {
      ZipEntry entry = entries.nextElement();
      String comment = entry.getComment();
      if (comment == null) {
        continue;
      }
      entriesByComment.add(comment, entry);
    }
    return entriesByComment;
  }
}