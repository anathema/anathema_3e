package net.sf.anathema.hero.combos.sheet.content;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import net.sf.anathema.hero.charms.display.MagicDisplayLabeler;
import net.sf.anathema.hero.combos.display.presenter.Combo;
import net.sf.anathema.library.resources.Resources;

import static net.sf.anathema.lib.lang.ArrayUtilities.transform;

public class ToDisplayCombo implements Function<Combo, DisplayCombo> {

  private Resources resources;

  public ToDisplayCombo(Resources resources) {
    this.resources = resources;
  }

  @Override
  public DisplayCombo apply(Combo combo) {
    String displayName = getDisplayName(combo);
    String displayCharms = getCharmString(combo);
    return new DisplayCombo(displayName, displayCharms);
  }

  private String getDisplayName(Combo combo) {
    String name = combo.getName().getText();
    return name == null ? "???" : name;
  }

  private String getCharmString(Combo combo) {
    MagicDisplayLabeler labeler = new MagicDisplayLabeler(resources);
    String[] charmNames = transform(combo.getCharms(), String.class, labeler::getLabelForMagic);
    return Joiner.on(", ").join(charmNames);
  }
}
