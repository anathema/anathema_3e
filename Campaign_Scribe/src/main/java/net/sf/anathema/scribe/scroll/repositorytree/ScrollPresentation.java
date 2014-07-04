package net.sf.anathema.scribe.scroll.repositorytree;

import net.sf.anathema.library.presenter.AgnosticUIConfiguration;
import net.sf.anathema.library.resources.RelativePath;
import net.sf.anathema.platform.item.IItemTypeViewProperties;
import net.sf.anathema.platform.repository.PrintNameFile;

public class ScrollPresentation implements IItemTypeViewProperties {
  private final ScrollUi ui;

  public ScrollPresentation() {
    this.ui = new ScrollUi();
  }

  @Override
  public RelativePath getIcon() {
    return AgnosticUIConfiguration.NO_ICON;
  }

  @Override
  public String getLabelKey() {
    return "Scribe.Export.ItemType";
  }

  @Override
  public AgnosticUIConfiguration<PrintNameFile> getItemTypeUI() {
    return ui;
  }
}