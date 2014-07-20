package net.sf.anathema.hero.sheet.pdf.encoder.table;

import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TableColumns {
  public static TableColumns singleColumn(float weight) {
    return from(weight);
  }

  public static TableColumns from(float... weights) {
    TableColumns columns = new TableColumns();
    for (float weight : weights) {
      columns.add(weight);
    }
    return columns;
  }

  private final List<Float> columnWeights = new ArrayList<Float>();

  public void add(float weight) {
    columnWeights.add(weight);
  }

  public void adopt(TableColumns columns) {
    columnWeights.addAll(columns.columnWeights);
  }

  public void changeLastTo(float weight) {
    columnWeights.remove(columnWeights.size() - 1);
    columnWeights.add(weight);
  }

  public int countWeights() {
    return columnWeights.size();
  }

  public boolean isEmpty() {
    return columnWeights.isEmpty();
  }

  public float[] asArray() {
    return ArrayUtils.toPrimitive(columnWeights.toArray(new Float[columnWeights.size()]));
  }

  public void addAll(Float[] movementColumns) {
    Collections.addAll(columnWeights, movementColumns);
  }
}
