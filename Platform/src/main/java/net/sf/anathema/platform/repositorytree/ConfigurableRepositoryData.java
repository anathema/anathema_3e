package net.sf.anathema.platform.repositorytree;

import net.sf.anathema.platform.item.IItemType;
import net.sf.anathema.platform.repository.RepositoryIdData;

public class ConfigurableRepositoryData implements RepositoryIdData {
  private final String id;
  private final IItemType type;

  public ConfigurableRepositoryData(String id, IItemType type) {
    this.id = id;
    this.type = type;
  }

  @Override
  public String getIdProposal() {
    return id;
  }

  @Override
  public IItemType getItemType() {
    return type;
  }
}