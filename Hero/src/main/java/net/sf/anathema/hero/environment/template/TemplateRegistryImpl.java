package net.sf.anathema.hero.environment.template;

import net.sf.anathema.hero.individual.splat.CharacterType;
import net.sf.anathema.hero.individual.splat.HeroSplat;
import net.sf.anathema.hero.individual.splat.SplatType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class TemplateRegistryImpl implements TemplateRegistry {

  private final HashMap<SplatType, HeroSplat> templatesByType = new HashMap<>();

  @Override
  public Collection<HeroSplat> getAllSupportedTemplates(CharacterType type) {
    List<HeroSplat> typeTemplates = new ArrayList<>();
    for (SplatType splatType : templatesByType.keySet()) {
      if (splatType.getCharacterType().equals(type)) {
        HeroSplat template = getTemplate(splatType);
        if (template != null) {
          typeTemplates.add(template);
        }
      }
    }
    return typeTemplates;
  }

  @Override
  public HeroSplat getTemplate(SplatType type) {
    return templatesByType.get(type);
  }

  @Override
  public void register(HeroSplat template) {
    templatesByType.put(template.getTemplateType(), template);
  }
}