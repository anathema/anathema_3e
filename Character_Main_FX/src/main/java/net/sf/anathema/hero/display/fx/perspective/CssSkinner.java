package net.sf.anathema.hero.display.fx.perspective;

import net.sf.anathema.hero.individual.splat.HeroType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.text.MessageFormat.format;

public class CssSkinner {

  public Collection<String> getSkins(HeroType heroType) {
    List<String> skins = new ArrayList<>();
    skins.add("skin/platform/dotselector.css");
    skins.add("skin/traits/favorable.css");
    skins.add(chooseSkinForCharacterType(heroType));
    return skins;
  }

  private String chooseSkinForCharacterType(HeroType heroType) {
    if (heroType == null) {
      return "skin/traits/trait.css";
    }
    String skinFolder = heroType.getId().toLowerCase();
    return format("skin/{0}/trait.css", skinFolder);
  }
}