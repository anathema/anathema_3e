package net.sf.anathema.hero.charms.model.special.subeffects;

import net.sf.anathema.hero.charms.model.special.CharmSpecialsModel;

public interface MultipleEffectCharmSpecials extends CharmSpecialsModel {
  Iterable<SubEffect> getEffects();

  SubEffect getEffectById(String id);
}