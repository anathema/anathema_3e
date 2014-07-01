package net.sf.anathema.character.equipment.creation.presenter.stats.properties;

public interface TagProperties<TAG> {
  String getLabel(TAG tag);

  String getToolTip(TAG tag);
}