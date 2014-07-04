package net.sf.anathema.hero.specialties.model;

public interface ISpecialtyListener {

  void subTraitValueChanged();

  void subTraitAdded(Specialty subTrait);

  void subTraitRemoved(Specialty subTrait);
}