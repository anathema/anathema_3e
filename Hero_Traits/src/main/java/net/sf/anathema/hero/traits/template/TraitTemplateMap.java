package net.sf.anathema.hero.traits.template;

import net.sf.anathema.hero.traits.model.TraitType;

public interface TraitTemplateMap {

  TraitTemplate getTemplate(TraitType type);
}