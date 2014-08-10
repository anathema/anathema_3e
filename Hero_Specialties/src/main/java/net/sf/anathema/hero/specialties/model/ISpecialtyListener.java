package net.sf.anathema.hero.specialties.model;

public interface ISpecialtyListener {

  void specialtyAdded(Specialty subTrait);

  void specialtyRemoved(Specialty subTrait);
}