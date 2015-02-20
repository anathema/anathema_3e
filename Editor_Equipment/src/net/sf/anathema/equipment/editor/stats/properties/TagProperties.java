package net.sf.anathema.equipment.editor.stats.properties;

public interface TagProperties<TAG> {
  String getLabel(TAG tag);

  String getToolTip(TAG tag);
}