package net.sf.anathema.lib.gui.selection;

import net.sf.anathema.library.event.ObjectChangedListener;

import java.util.Collection;

public interface ObjectSelectionView<V> {

  void setSelectedObject(V object);

  void addObjectSelectionChangedListener(ObjectChangedListener<V> listener);

  void removeObjectSelectionChangedListener(ObjectChangedListener<V> listener);

  void setObjects(V[] objects);

  void setObjects(Collection<V> objects);

  V getSelectedObject();

  void setEnabled(boolean enabled);

  void clearSelection();
}