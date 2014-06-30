package net.sf.anathema.hero.magic.charm.type;

public interface IReflexiveSpecialsModel extends ITypeSpecialsModel {

  Integer getPrimaryStep();

  Integer getSecondaryStep();

  boolean isSplitEnabled();
}