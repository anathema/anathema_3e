package net.sf.anathema.hero.concept.dummy;

import net.sf.anathema.hero.concept.model.concept.CasteSelection;
import net.sf.anathema.hero.concept.model.concept.CasteType;
import net.sf.anathema.library.event.ChangeListener;

public class NullCasteSelection implements CasteSelection{
  @Override
  public CasteType getType() {
    return CasteType.NULL_CASTE_TYPE;
  }

  @Override
  public void setType(CasteType type) {
    //nothing to do
  }

  @Override
  public void addChangeListener(ChangeListener listener) {
    //nothing to do
  }

  @Override
  public boolean isNotSelected() {
    return true;
  }
}