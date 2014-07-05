package net.sf.anathema.hero.concept.model.concept;

public class CasteModelImpl {

  private final CasteSelection selection;
  private final CasteCollection collection;

  public CasteModelImpl(CasteSelection selection, CasteCollection collection) {
    this.selection = selection;
    this.collection = collection;
  }

  public CasteSelection getSelection() {
    return selection;
  }

  public CasteCollection getCollection() {
    return collection;
  }
}