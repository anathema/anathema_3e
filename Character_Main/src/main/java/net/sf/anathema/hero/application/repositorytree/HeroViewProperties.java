package net.sf.anathema.hero.application.repositorytree;

import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.presenter.view.IItemTypeViewProperties;
import net.sf.anathema.framework.view.PrintNameFile;
import net.sf.anathema.hero.application.item.HeroReferenceScanner;
import net.sf.anathema.hero.framework.CharacterUI;
import net.sf.anathema.library.presenter.AgnosticUIConfiguration;
import net.sf.anathema.library.resources.RelativePath;
import net.sf.anathema.library.resources.Resources;

public class HeroViewProperties implements IItemTypeViewProperties {

  private final RelativePath icon;
  private final IItemType type;
  private final HeroFileUi ui;

  public HeroViewProperties(IItemType type, Resources resources, HeroReferenceScanner scanner) {
    this.type = type;
    this.icon = new CharacterUI().getCharacterDescriptionTabIcon();
    this.ui = new HeroFileUi(resources, scanner);
  }

  @Override
  public RelativePath getIcon() {
    return icon;
  }

  @Override
  public AgnosticUIConfiguration<PrintNameFile> getItemTypeUI() {
    return ui;
  }

  @Override
  public String getLabelKey() {
    return "ItemType." + type.getId() + ".PrintName";
  }
}