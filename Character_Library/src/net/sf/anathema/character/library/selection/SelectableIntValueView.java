package net.sf.anathema.character.library.selection;

import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.sf.anathema.character.library.intvalue.IIntValueDisplayFactory;
import net.sf.anathema.character.library.intvalue.NullUpperBounds;
import net.sf.anathema.framework.value.IIntValueDisplay;
import net.sf.anathema.lib.control.GenericControl;
import net.sf.anathema.lib.control.IClosure;
import net.sf.anathema.lib.control.intvalue.IIntValueChangedListener;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;
import net.sf.anathema.lib.gui.selection.ISelectionIntValueChangedListener;
import net.sf.anathema.lib.gui.widgets.ChangeableJComboBox;
import net.sf.anathema.lib.gui.widgets.IChangeableJComboBox;

import javax.swing.*;
import java.awt.*;

public class SelectableIntValueView<V> implements ISelectableIntValueView<V> {

  private final IChangeableJComboBox<V> objectSelectionBox = new ChangeableJComboBox<V>(false);

  private final IIntValueDisplay valueDisplay;
  private final GenericControl<ISelectionIntValueChangedListener<V>> control = new GenericControl<ISelectionIntValueChangedListener<V>>();
  private int currentValue;

  public SelectableIntValueView(IIntValueDisplayFactory configuration, int initial, int max) {
    this.valueDisplay = configuration.createIntValueDisplay(max, initial, new NullUpperBounds());
    objectSelectionBox.addObjectSelectionChangedListener(new IObjectValueChangedListener<V>() {
      public void valueChanged(V newValue) {
        fireSelectionChangedEvent();
      }
    });
    valueDisplay.addIntValueChangedListener(new IIntValueChangedListener() {
      public void valueChanged(int newValue) {
    	int oldValue = currentValue;
    	currentValue = newValue;
    	if (oldValue != newValue) {
    		valueDisplay.setValue(newValue);
    	}
        fireSelectionChangedEvent();
      }
    });
  }

  private void fireSelectionChangedEvent() {
    control.forAllDo(new IClosure<ISelectionIntValueChangedListener<V>>() {
      public void execute(ISelectionIntValueChangedListener<V> input) {
    	input.valueChanged(objectSelectionBox.getSelectedObject(), currentValue);
      }
    });
  }

  public JComponent getContent() {
    JPanel panel = new JPanel(new GridDialogLayout(2, false));
    addTo(panel);
    return panel;
  }

  public void addTo(JPanel panel) {
    panel.add(objectSelectionBox.getComponent(), GridDialogLayoutData.FILL_HORIZONTAL);
    panel.add(valueDisplay.getComponent());
    panel.revalidate();
  }

  public void setSelectableValues(V[] objects) {
    objectSelectionBox.setObjects(objects);
  }

  public void addSelectionChangedListener(ISelectionIntValueChangedListener<V> listener) {
    control.addListener(listener);
  }

  public void setSelectedObject(V object) {
    objectSelectionBox.setSelectedObject(object);
  }

  public void setValue(int value) {
    valueDisplay.setValue(value);
  }

  public JComponent getSelectionComponent() {
    return objectSelectionBox.getComponent();
  }

  public Component getValueComponent() {
    return valueDisplay.getComponent();
  }
}