package net.sf.anathema.hero.charms.display;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import net.sf.anathema.library.event.IntegerChangedListener;
import net.sf.anathema.library.fx.dot.FxDotView;
import net.sf.anathema.library.view.IntValueView;
import net.sf.anathema.platform.tree.fx.FxSpecialContent;
import org.tbee.javafx.scene.layout.MigPane;

import static net.sf.anathema.library.fx.layout.LayoutUtils.fillWithoutInsets;

public class FxIntValueView implements FxSpecialContent, IntValueView {
  private FxDotView view;

  public FxIntValueView(FxDotView view) {
    this.view = view;
  }

  @Override
  public void setValue(int newValue) {
    view.setValue(newValue);
  }

  @Override
  public void addIntValueChangedListener(IntegerChangedListener listener) {
    view.addIntValueChangedListener(listener);
  }

  @Override
  public void removeIntValueChangedListener(IntegerChangedListener listener) {
    view.removeIntValueChangedListener(listener);
  }

  @Override
  public void addTo(ContextMenu menu) {
    MigPane container = new MigPane(fillWithoutInsets().wrapAfter(2));
    view.addTo(container);
    menu.getItems().add(new MenuItem("", container));
  }
}