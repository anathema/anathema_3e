package net.sf.anathema.scribe.scroll.repositorytree;


import net.sf.anathema.library.resources.Resources;
import net.sf.anathema.platform.frame.ApplicationModel;
import net.sf.anathema.platform.item.ForItemType;
import net.sf.anathema.platform.item.IItemTypeViewProperties;
import net.sf.anathema.platform.item.ItemTypePresentationFactory;

import static net.sf.anathema.scribe.scroll.ScrollItemType.SCROLL_ITEM_TYPE_ID;

@ForItemType(SCROLL_ITEM_TYPE_ID)
public class ScrollPresentationFactory implements ItemTypePresentationFactory {
  @Override
  public IItemTypeViewProperties createItemTypeCreationProperties(ApplicationModel anathemaModel, Resources resources) {
    return new ScrollPresentation();
  }
}
