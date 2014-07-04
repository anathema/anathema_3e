package net.sf.anathema.hero.framework.display;

import net.sf.anathema.hero.application.item.Item;
import net.sf.anathema.hero.individual.splat.HeroSplat;
import net.sf.anathema.library.exception.PersistenceException;

public interface IItemCreator {

  Item createItem(HeroSplat template) throws PersistenceException;
}