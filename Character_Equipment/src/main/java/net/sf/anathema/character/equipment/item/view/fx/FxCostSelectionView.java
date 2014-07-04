package net.sf.anathema.character.equipment.item.view.fx;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import net.miginfocom.layout.CC;
import net.sf.anathema.character.equipment.item.view.CostSelectionView;
import net.sf.anathema.equipment.core.ItemCost;
import net.sf.anathema.library.event.ObjectChangedListener;
import net.sf.anathema.library.event.SelectionIntValueChangedListener;
import net.sf.anathema.library.fx.dot.DotSelectionSpinner;
import net.sf.anathema.library.fx.selection.FxObjectSelectionView;
import net.sf.anathema.library.fx.selection.SelectionViewFactory;
import org.jmock.example.announcer.Announcer;
import org.tbee.javafx.scene.layout.MigPane;

import java.util.Collection;

import static net.sf.anathema.library.fx.layout.LayoutUtils.withoutInsets;

public class FxCostSelectionView implements CostSelectionView {

  private FxObjectSelectionView<String> selection;
  private final DotSelectionSpinner spinner = new DotSelectionSpinner(0, 5);
  private final MigPane pane = new MigPane(withoutInsets());
  private final Announcer<SelectionIntValueChangedListener> announcer = new Announcer<>(
    SelectionIntValueChangedListener.class);
  private final CostTypeChangeListener typeChangeListener = new CostTypeChangeListener();

  public FxCostSelectionView(final String text, SelectionViewFactory viewFactory) {
    selection = viewFactory.create(text, new SimpleUiConfiguration());
    pane.add(selection.getNode());
    pane.add(spinner.getNode(), new CC().alignY("center"));
    selection.addObjectSelectionChangedListener(typeChangeListener);
    CostValueChangeListener valueChangeListener = new CostValueChangeListener();
    spinner.addListener(valueChangeListener);
  }

  @Override
  public void setValue(final ItemCost cost) {
    if (cost == null) {
      selectTypeSilently(null);
      spinner.setValueSilently(0);
    } else {
      selectTypeSilently(cost.getType());
      spinner.setValueSilently(cost.getValue());
    }
  }

  private void selectTypeSilently(String type) {
    selection.removeObjectSelectionChangedListener(typeChangeListener);
    selection.setSelectedObject(type);
    selection.addObjectSelectionChangedListener(typeChangeListener);
  }

  @Override
  public void addSelectionChangedListener(final SelectionIntValueChangedListener<String> listener) {
    announcer.addListener(listener);
  }

  @Override
  public void setSelectableBackgrounds(Collection<String> backgrounds) {
    selection.setObjects(backgrounds);
  }

  public Node getNode() {
    return pane;
  }

  @SuppressWarnings("unchecked")
  private class CostTypeChangeListener implements ObjectChangedListener<String> {
    @Override
    public void valueChanged(String newValue) {
      announcer.announce().valueChanged(newValue, spinner.getValue());
    }
  }

  @SuppressWarnings("unchecked")
  private class CostValueChangeListener implements ChangeListener<Integer> {
    @Override
    public void changed(ObservableValue<? extends Integer> observableValue, Integer integer, Integer newValue) {
      announcer.announce().valueChanged(selection.getSelectedObject(), newValue);
    }
  }
}