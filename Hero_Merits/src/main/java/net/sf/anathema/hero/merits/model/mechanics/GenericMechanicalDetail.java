package net.sf.anathema.hero.merits.model.mechanics;

import net.sf.anathema.hero.merits.model.MechanicalDetailReference;

import java.util.HashMap;
import java.util.Map;

public class GenericMechanicalDetail implements MechanicalDetail {

  private final Map<DetailEntryReference, Object> details = new HashMap<>();
  private final MechanicalDetailReference reference;

  public GenericMechanicalDetail(String reference) {
    this.reference = new MechanicalDetailReference(reference);
  }

  public void addDetailEntry(DetailEntryReference reference, Object entry) {
    details.put(reference, entry);
  }
  
  @SuppressWarnings("unchecked")
  @Override
  public <T> T getEntry(DetailEntryReference reference) {
    return (T) details.get(reference);
  }

  @Override
  public boolean isReferencedBy(MechanicalDetailReference reference) {
    return this.reference.equals(reference);
  }
}