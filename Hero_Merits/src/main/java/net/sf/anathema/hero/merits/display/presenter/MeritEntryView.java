package net.sf.anathema.hero.merits.display.presenter;

import net.sf.anathema.library.event.ObjectChangedListener;
import net.sf.anathema.library.interaction.model.Tool;
import net.sf.anathema.library.presenter.AgnosticUIConfiguration;
import net.sf.anathema.library.view.ObjectSelectionView;

public interface MeritEntryView {
	
  void addTextChangeListener(ObjectChangedListener<String> listener);

  Tool addTool();

  void clear();
  
  void addDescriptionBox(String label);

  <T> ObjectSelectionView<T> addSelection(AgnosticUIConfiguration<T> uiConfiguration);

  <T> ObjectSelectionView<T> addMeritSelection(AgnosticUIConfiguration<T> uiConfiguration);
}