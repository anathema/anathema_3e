package net.sf.anathema.hero.framework.persistence;

import net.sf.anathema.hero.application.item.Item;
import net.sf.anathema.hero.template.HeroTemplate;
import net.sf.anathema.platform.repository.access.RepositoryReadAccess;
import net.sf.anathema.platform.repository.access.RepositoryWriteAccess;

public interface RepositoryItemPersister {

  void save(RepositoryWriteAccess writeAccess, Item item);

  Item load(RepositoryReadAccess readAccess);

  Item createNew(HeroTemplate template);
}