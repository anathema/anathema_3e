package net.sf.anathema.hero.traits.template;

import net.sf.anathema.hero.traits.model.TraitType;

public class TraitTemplateMapImpl implements TraitTemplateMap {

  private GroupedTraitsTemplate template;

  public TraitTemplateMapImpl(GroupedTraitsTemplate template) {
    this.template = template;
  }

  @Override
  public TraitTemplate getTemplate(TraitType type) {
    return template.standard;
  }
}
