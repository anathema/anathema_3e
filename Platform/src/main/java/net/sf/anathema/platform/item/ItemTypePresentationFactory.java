package net.sf.anathema.platform.item;

import net.sf.anathema.library.resources.Resources;
import net.sf.anathema.platform.frame.ApplicationModel;

public interface ItemTypePresentationFactory {

  IItemTypeViewProperties createItemTypeCreationProperties(ApplicationModel anathemaModel, Resources resources);
}
