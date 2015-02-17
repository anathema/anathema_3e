package net.sf.anathema.hero.application.perspective;

import net.sf.anathema.hero.application.perspective.model.CharacterItemModel;
import net.sf.anathema.hero.application.perspective.model.HeroIdentifier;
import net.sf.anathema.library.collection.RotatingSet;
import net.sf.anathema.platform.preferences.PreferenceKey;
import net.sf.anathema.platform.preferences.PreferencePto;
import net.sf.anathema.platform.preferences.PreferenceValue;

import java.util.Collection;
import java.util.Map;

public class RecentHeroes {
  private static final int MAXIMUM_RECENT_HEROES = 5;
  private final RotatingSet<CharacterItemModel> recentHeroes = new RotatingSet<>(MAXIMUM_RECENT_HEROES);

  public void initializeFrom(PreferencePto recentHeroesPto, Map<HeroIdentifier, CharacterItemModel> modelsByIdentifier) {
    if (recentHeroesPto.map.isEmpty()) {
      return;
    }
    int upperBound = Math.min(MAXIMUM_RECENT_HEROES, recentHeroesPto.map.size());
    for (int heroIndex = 0; heroIndex < upperBound; heroIndex++) {
      PreferenceValue value = recentHeroesPto.map.get(createHeroKey(heroIndex));
      HeroIdentifier identifier = new HeroIdentifier(value.value);
      if (!modelsByIdentifier.containsKey(identifier)) {
        continue;
      }
      CharacterItemModel hero = modelsByIdentifier.get(identifier);
      recentHeroes.add(hero);
    }
  }

  public Collection<CharacterItemModel> asCollection() {
    return recentHeroes;
  }

  public void add(CharacterItemModel hero) {
    recentHeroes.add(hero);
  }

  public PreferencePto serialize() {
    PreferencePto recentHeroesPto = new PreferencePto();
    for (CharacterItemModel hero : recentHeroes) {
      String id = hero.getDescriptiveFeatures().getIdentifier().getId();
      PreferenceKey key = createHeroKey(recentHeroesPto.map.size());
      PreferenceValue value = new PreferenceValue(id);
      recentHeroesPto.map.put(key, value);
    }
    return recentHeroesPto;
  }

  private PreferenceKey createHeroKey(int heroIndex) {
    return new PreferenceKey("recenthero." + heroIndex);
  }
}