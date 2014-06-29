package net.sf.anathema.hero.template.points;

import net.sf.anathema.lib.lang.clone.ICloneable;

public interface IAbilityCreationPoints extends ICloneable<IAbilityCreationPoints> {

  int getFavoredDotCount();

  int getFavorableTraitCount();

  int getDefaultDotCount();
}