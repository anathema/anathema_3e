package net.sf.anathema.hero.charms.model.special.subeffects;

import java.util.List;

public interface SubEffects extends Iterable<SubEffect> {
  List<SubEffect> getEffects();

  SubEffect getById(String id);
}