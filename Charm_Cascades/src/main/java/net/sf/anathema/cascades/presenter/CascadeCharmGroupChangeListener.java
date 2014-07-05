package net.sf.anathema.cascades.presenter;

import net.sf.anathema.charm.data.reference.CategoryReference;
import net.sf.anathema.hero.charms.display.presenter.AbstractCharmGroupChangeListener;
import net.sf.anathema.hero.charms.display.presenter.CharmDisplayPropertiesMap;
import net.sf.anathema.hero.individual.splat.HeroType;
import net.sf.anathema.library.identifier.Identifier;
import net.sf.anathema.library.presenter.RGBColor;
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
    if (type instanceof HeroType) {
      TreePresentationProperties displayProperties = getDisplayProperties((HeroType) type);
      return displayProperties.getColor();
    } else {
      return RGBColor.White;
    }
  }

  protected TreePresentationProperties getDisplayProperties(HeroType heroType) {
    return displayPropertiesMap.getDisplayProperties(heroType);
  }
}