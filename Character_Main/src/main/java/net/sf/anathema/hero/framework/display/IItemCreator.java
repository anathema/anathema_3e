package net.sf.anathema.hero.framework.display;

import net.sf.anathema.hero.template.HeroTemplate;
import net.sf.anathema.hero.framework.item.Item;
import net.sf.anathema.lib.exception.PersistenceException;

public interface IItemCreator {

  Item createItem(HeroTemplate template) throws PersistenceException;
}