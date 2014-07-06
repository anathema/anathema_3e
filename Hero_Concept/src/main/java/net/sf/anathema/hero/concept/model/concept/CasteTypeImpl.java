package net.sf.anathema.hero.concept.model.concept;

public class CasteTypeImpl implements CasteType {

  private String id;

  public CasteTypeImpl(String id) {
    this.id = id;
  }

  @Override
  public String getId() {
    return id;
  }
}