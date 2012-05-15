package net.sf.anathema.framework.presenter.view;

import net.sf.anathema.lib.gui.swing.IDisposable;

public interface IViewContent {

  public abstract void addTo(IMultiContentView view);

  public abstract IDisposable getDisposable();
}