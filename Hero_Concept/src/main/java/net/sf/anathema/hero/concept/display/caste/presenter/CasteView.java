package net.sf.anathema.hero.concept.display.caste.presenter;

import net.sf.anathema.hero.concept.model.concept.CasteType;
import net.sf.anathema.library.presenter.AgnosticUIConfiguration;
import net.sf.anathema.library.view.ObjectSelectionView;

public interface CasteView {

  ObjectSelectionView<CasteType> addObjectSelectionView(String labelText, AgnosticUIConfiguration<CasteType> renderer);
}