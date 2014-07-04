package net.sf.anathema.hero.framework.display;

import net.sf.anathema.hero.application.item.Item;
import net.sf.anathema.hero.template.HeroTemplate;
import net.sf.anathema.library.exception.PersistenceException;

public interface IItemCreator {

  Item createItem(HeroTemplate template) throws PersistenceException;
}