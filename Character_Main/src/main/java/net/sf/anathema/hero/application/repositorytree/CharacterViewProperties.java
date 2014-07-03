package net.sf.anathema.hero.application.repositorytree;

import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.presenter.view.IItemTypeViewProperties;
import net.sf.anathema.framework.view.PrintNameFile;
import net.sf.anathema.hero.framework.CharacterUI;
import net.sf.anathema.hero.framework.item.CharacterReferenceScanner;
import net.sf.anathema.lib.file.RelativePath;
import net.sf.anathema.lib.gui.AgnosticUIConfiguration;

public class CharacterViewProperties implements IItemTypeViewProperties {

  private final RelativePath icon;
  private final IItemType type;
  private final CharacterFileUi ui;

  public CharacterViewProperties(IItemType type, Resources resources, CharacterReferenceScanner scanner) {
    this.type = type;
    this.icon = new CharacterUI().getCharacterDescriptionTabIcon();
    this.ui = new CharacterFileUi(resources, scanner);
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