package net.sf.anathema.hero.magic.charm.type;

public interface ICharmTypeModel {

  CharmType getCharmType();

  ITypeSpecialsModel getSpecialsModel();

  boolean hasSpecialsModel();
}