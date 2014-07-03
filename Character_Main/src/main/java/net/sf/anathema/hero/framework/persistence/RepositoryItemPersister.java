package net.sf.anathema.hero.framework.persistence;

import net.sf.anathema.framework.repository.access.RepositoryReadAccess;
import net.sf.anathema.framework.repository.access.RepositoryWriteAccess;
import net.sf.anathema.hero.framework.item.Item;
import net.sf.anathema.hero.template.HeroTemplate;

public interface RepositoryItemPersister {

  void save(RepositoryWriteAccess writeAccess, Item item);

  Item load(RepositoryReadAccess readAccess);

  Item createNew(HeroTemplate template);
}