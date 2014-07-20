package net.sf.anathema.library.fx.dot;

import javafx.scene.Node;
import javafx.scene.control.Label;
import net.miginfocom.layout.CC;
import net.sf.anathema.library.fx.layout.LayoutUtils;
import org.tbee.javafx.scene.layout.MigPane;

import java.util.ArrayList;
import java.util.List;

public class FxGroupedColumnPanel implements DotViewPanel {
  private final List<MigPane> columns = new ArrayList<>();
  private int columnIndex = -1;

  public FxGroupedColumnPanel(MigPane pane, ColumnCount columnCount) {
    for (int i = 0; i < columnCount.getColumnCount(); i++) {
      columns.add(new MigPane(LayoutUtils.withoutInsets().wrapAfter(3)));
    }
    addColumnsToContainer(pane);
  }

  private void increaseColumnIndex() {
    columnIndex++;
    if (columnIndex >= columns.size()) {
      columnIndex = 0;
    }
  }

  private void addColumnsToContainer(MigPane container) {
    container.setLayoutConstraints(LayoutUtils.withoutInsets().wrapAfter(columns.size()).gridGapX("15"));
    for (MigPane column : columns) {
      container.add(column, new CC().alignY("top"));
    }
  }

  public void startNewGroup(final String groupLabel) {
    increaseColumnIndex();
    if (groupLabel != null) {
      getCurrentColumn().add(new Label(groupLabel), new CC().gapTop("5").spanX().growX().pushX());
    }
  }

  public MigPane getCurrentColumn() {
    return columns.get(columnIndex);
  }

  @Override
  public void add(Node node) {
    add(node, new CC());
  }

  @Override
  public void add(final Node node, final CC constraints) {
    getCurrentColumn().add(node, constraints);
  }

  @Override
  public void remove(Node node) {
    getCurrentColumn().getChildren().remove(node);
  }
}