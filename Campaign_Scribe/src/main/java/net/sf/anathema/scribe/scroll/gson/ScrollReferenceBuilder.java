package net.sf.anathema.scribe.scroll.gson;

import net.sf.anathema.platform.repository.printname.ReferenceBuilder;
import net.sf.anathema.scribe.scroll.persistence.Scroll;
import net.sf.anathema.scribe.scroll.persistence.ScrollReference;

public class ScrollReferenceBuilder implements ReferenceBuilder<ScrollReference> {
  @Override
  public ScrollReference create(String itemSaveData) {
    Scroll scroll = new ScrollGson().fromJson(itemSaveData);
    return new ScrollReference(scroll.repositoryId, scroll.dto.title);
  }
}
