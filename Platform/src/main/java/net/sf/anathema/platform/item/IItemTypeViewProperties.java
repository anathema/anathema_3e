package net.sf.anathema.platform.item;

import net.sf.anathema.library.presenter.AgnosticUIConfiguration;
import net.sf.anathema.library.resources.RelativePath;
import net.sf.anathema.platform.repository.PrintNameFile;

public interface IItemTypeViewProperties {

  RelativePath getIcon();

  String getLabelKey();

  AgnosticUIConfiguration<PrintNameFile> getItemTypeUI();
}