package net.sf.anathema.platform.repository;

import net.sf.anathema.platform.item.IItemType;

public interface RepositoryIdData {
  String getIdProposal();

  IItemType getItemType();
}