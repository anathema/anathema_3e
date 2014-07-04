package net.sf.anathema.hero.framework.perspective;

import net.sf.anathema.hero.application.item.Item;
import net.sf.anathema.hero.creation.IItemOperator;
import net.sf.anathema.hero.framework.display.IItemCreator;
import net.sf.anathema.hero.framework.display.ItemReceiver;
import net.sf.anathema.hero.individual.splat.HeroSplat;
import net.sf.anathema.library.exception.PersistenceException;

public class ItemCreator implements IItemOperator {

  private final IItemCreator creator;
  private final ItemReceiver receiver;

  public ItemCreator(IItemCreator creator, ItemReceiver receiver) {
    this.creator = creator;
    this.receiver = receiver;
  }

  @Override
  public void operate(HeroSplat template) throws PersistenceException {
    Item item = creator.createItem(template);
    try {
      receiver.addItem(item);
    } catch (Throwable e) {
      throw new PersistenceException(e);
    }
  }
}