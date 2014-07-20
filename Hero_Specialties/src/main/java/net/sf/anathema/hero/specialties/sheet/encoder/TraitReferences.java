package net.sf.anathema.hero.specialties.sheet.encoder;

import net.sf.anathema.hero.traits.sheet.content.ValuedTraitReference;

import java.util.ArrayList;
import java.util.List;

public class TraitReferences {
  private final List<ValuedTraitReference> references;

  public TraitReferences() {
    this(new ArrayList<>());
  }

  public TraitReferences(List<ValuedTraitReference> references) {
    this.references = references;
  }
  
  public int countTraits() {
    return references.size();
  }

  public Iterable<ValuedTraitReference> forElementsFromOneTo(int lineCount) {
    return references.subList(0, lineCount-1);
  }
}
