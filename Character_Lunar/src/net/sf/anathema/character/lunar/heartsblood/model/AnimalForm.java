package net.sf.anathema.character.lunar.heartsblood.model;

import net.sf.anathema.character.lunar.heartsblood.presenter.IAnimalForm;

public class AnimalForm implements IAnimalForm {

  private final String name;
  private final int strength;
  private final int dexterity;
  private final int stamina;
  private final int appearance;
  private final boolean experienceLearned;

  public AnimalForm(String name, int strength, int dexterity, 
		  int stamina, int appearance, boolean experienceLearned) {
    this.name = name;
    this.strength = strength;
    this.dexterity = dexterity;
    this.stamina = stamina;
    this.appearance = appearance;
    this.experienceLearned = experienceLearned;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public int getStrength() {
    return strength;
  }
  
  @Override
  public int getDexterity()
  {
	  return dexterity;
  }

  @Override
  public int getStamina() {
    return stamina;
  }
  
  @Override
  public int getAppearance()
  {
	  return appearance;
  }

  @Override
  public boolean isCreationLearned() {
    return !experienceLearned;
  }
}