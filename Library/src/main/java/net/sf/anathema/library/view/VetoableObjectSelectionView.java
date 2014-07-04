package net.sf.anathema.library.view;

import net.sf.anathema.library.presenter.AgnosticUIConfiguration;

public interface VetoableObjectSelectionView<V> extends ObjectSelectionView<V> {

  void addSelectionVetor(Vetor vetor);

  @SuppressWarnings("UnusedDeclaration")
  void removeSelectionVetor(Vetor vetor);

  void setCellRenderer(AgnosticUIConfiguration<V> renderer);
}