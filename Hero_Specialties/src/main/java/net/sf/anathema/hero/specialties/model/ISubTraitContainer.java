package net.sf.anathema.hero.specialties.model;

import java.util.Collection;

public interface ISubTraitContainer {

  Collection<Specialty> getSubTraits();

  Specialty addSubTrait(String subName);

  boolean isNewSubTraitAllowed();

  void removeSubTrait(Specialty specialty);

  void addSubTraitListener(ISpecialtyListener listener);

  void removeSubTraitListener(ISpecialtyListener listener);

  int getCreationDotTotal();

  int getCurrentDotTotal();

  int getExperienceDotTotal();

  boolean isRemovable(Specialty subTrait);

  void dispose();

  Specialty getSubTrait(String traitName);
}