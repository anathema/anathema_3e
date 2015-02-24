package net.sf.anathema.charm.template.special.learning;

public class Requirement {
  public String traitType;
  public int traitValue;
  
  public Requirement() {
  	
  }
  
  public Requirement(String traitType, int traitValue) {
  	this.traitType = traitType;
  	this.traitValue = traitValue;
  }
}
