package net.sf.anathema.hero.combos.sheet.content;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.hero.charms.display.MagicDisplayLabeler;
import net.sf.anathema.hero.combos.display.presenter.Combo;
import net.sf.anathema.library.resources.Resources;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

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
    Stream<Charm> comboCharms = Stream.of(combo.getCharms());
    List<String> charmNames = comboCharms.map(labeler::getLabelForMagic).collect(toList());
    return Joiner.on(", ").join(charmNames);
  }
}
