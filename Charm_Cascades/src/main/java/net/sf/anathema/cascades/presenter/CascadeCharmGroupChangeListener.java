package net.sf.anathema.cascades.presenter;

import net.sf.anathema.charm.data.reference.CategoryReference;
import net.sf.anathema.framework.ui.RGBColor;
import net.sf.anathema.hero.charms.display.presenter.AbstractCharmGroupChangeListener;
import net.sf.anathema.hero.charms.display.presenter.CharmDisplayPropertiesMap;
import net.sf.anathema.hero.framework.type.CharacterType;
import net.sf.anathema.library.identifier.Identifier;
import net.sf.anathema.platform.tree.document.visualizer.TreePresentationProperties;

public class CascadeCharmGroupChangeListener extends AbstractCharmGroupChangeListener {

  private final CascadeSpecialCharmSet specialCharmSet;
  private final CharmDisplayPropertiesMap displayPropertiesMap;

  public CascadeCharmGroupChangeListener(CascadeSpecialCharmSet specialCharmSet, CharmDisplayPropertiesMap propertiesMap) {
    super(new FriendlyCharmTreeArbitrator());
    this.specialCharmSet = specialCharmSet;
    this.displayPropertiesMap = propertiesMap;
  }

  @Override
  protected final void modifyCharmVisuals(CategoryReference type) {
    specialCharmSet.setCategory(type);
    RGBColor color = findColor(type);
    getTreeView().setCanvasBackground(color);
  }

  private RGBColor findColor(Identifier type) {
    if (type instanceof CharacterType) {
      TreePresentationProperties displayProperties = getDisplayProperties((CharacterType) type);
      return displayProperties.getColor();
    } else {
      return RGBColor.White;
    }
  }

  protected TreePresentationProperties getDisplayProperties(CharacterType characterType) {
    return displayPropertiesMap.getDisplayProperties(characterType);
  }
}