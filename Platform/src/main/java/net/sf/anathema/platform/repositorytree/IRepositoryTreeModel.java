package net.sf.anathema.platform.repositorytree;

import net.sf.anathema.library.event.ChangeListener;
import net.sf.anathema.platform.item.IItemType;
import net.sf.anathema.platform.repository.PrintNameFile;
import net.sf.anathema.platform.repository.access.RepositoryWriteAccess;

import java.util.Collection;

public interface IRepositoryTreeModel extends ExportModel {
  IItemType[] getAllItemTypes();

  IItemType getItemTypeForId(String id);

  Collection<PrintNameFile> getPrintNameFiles(IItemType itemType);

  void addRepositoryTreeModelListener(IRepositoryTreeModelListener listener);

  void deleteSelection();

  boolean canSelectionBeDeleted();

  String getRepositoryPath();

  void setSelectedObject(Object[] object);

  void addTreeSelectionChangeListener(ChangeListener changeListener);

  String createUniqueId(IItemType type, String id);

  RepositoryWriteAccess getWriteAccess(IItemType type, String id);

  String getMainFilePath(IItemType type, String id);

  void refreshItem(IItemType type, String id);
}