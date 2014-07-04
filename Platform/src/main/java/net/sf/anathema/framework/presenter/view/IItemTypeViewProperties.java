package net.sf.anathema.framework.presenter.view;

import net.sf.anathema.framework.view.PrintNameFile;
import net.sf.anathema.library.presenter.AgnosticUIConfiguration;
import net.sf.anathema.library.resources.RelativePath;

public interface IItemTypeViewProperties {

  RelativePath getIcon();

  String getLabelKey();

  AgnosticUIConfiguration<PrintNameFile> getItemTypeUI();
}