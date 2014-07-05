package net.sf.anathema.hero.concept.model.concept;

public class ConfigurableCasteType implements CasteType {

  private String id;
  private String[] traits;

  public ConfigurableCasteType(String id, String[] traits) {
    this.id = id;
    this.traits = traits;
  }

  @Override
  public String getId() {
    return id;
  }

  @Override
  public String[] getCasteTraitIds() {
	  return traits;
  }
}