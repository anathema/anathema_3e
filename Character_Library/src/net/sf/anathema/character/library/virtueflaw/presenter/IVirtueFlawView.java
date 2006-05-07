package net.sf.anathema.character.library.virtueflaw.presenter;

import javax.swing.ListCellRenderer;

import net.sf.anathema.framework.presenter.view.ISimpleTabView;
import net.sf.anathema.lib.gui.selection.IObjectSelectionView;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;

public interface IVirtueFlawView extends ISimpleTabView {

  public ITextView addTextView(String label, int columnCount);

  public IObjectSelectionView addVirtueFlawRootSelectionView(String string, ListCellRenderer renderer);

  public void setEnabled(boolean enabled);
}