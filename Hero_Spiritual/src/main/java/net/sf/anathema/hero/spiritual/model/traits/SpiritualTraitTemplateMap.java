package net.sf.anathema.hero.spiritual.model.traits;

import net.sf.anathema.hero.spiritual.template.SpiritualTraitsTemplate;
import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.hero.traits.template.TraitTemplateMap;
import net.sf.anathema.hero.traits.model.types.OtherTraitType;
import net.sf.anathema.hero.traits.template.TraitTemplate;

import java.util.HashMap;
import java.util.Map;

public class SpiritualTraitTemplateMap implements TraitTemplateMap {
  private Map<TraitType, TraitTemplate> templatesByType = new HashMap<>();

  public SpiritualTraitTemplateMap(SpiritualTraitsTemplate template) {
    templatesByType.put(OtherTraitType.Essence, template.essence);
    templatesByType.put(OtherTraitType.Willpower, template.willpower);
  }

  @Override
  public TraitTemplate getTemplate(TraitType type) {
    return templatesByType.get(type);
  }
}
