package net.sf.anathema.library.fx.cell;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public interface ListCellFactory<T> extends Callback<ListView<T>, ListCell<T>> {
  //more readable interface
}
