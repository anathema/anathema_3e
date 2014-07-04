package net.sf.anathema.framework.item;

import net.sf.anathema.library.identifier.Identifier;

public interface IItemType extends Identifier {

  RepositoryConfiguration getRepositoryConfiguration();

  boolean isIntegrated();
}