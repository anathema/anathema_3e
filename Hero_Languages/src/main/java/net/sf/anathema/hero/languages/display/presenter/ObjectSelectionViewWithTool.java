package net.sf.anathema.hero.languages.display.presenter;

import net.sf.anathema.library.interaction.model.Tool;
import net.sf.anathema.library.view.ObjectSelectionView;

public interface ObjectSelectionViewWithTool<V> extends ObjectSelectionView<V> {

  Tool addTool();
}