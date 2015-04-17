package net.sf.anathema.hero.merits.advance.calculator;

import net.sf.anathema.hero.merits.model.Merit;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FreeMerits {
  private Map<String, Integer> freeMerits;

  public FreeMerits(Map<String, Integer> freeMerits) {
    this.freeMerits = freeMerits;
  }

  public List<Merit> cover(List<Merit> possessedMerits) {
    List<Merit> meritsNotYetCovered = new ArrayList<>();
    for (Merit merit : possessedMerits) {
      String meritId = merit.getBaseOption().getTraitType().getId();
      if (!freeMerits.containsKey(meritId)) {
        meritsNotYetCovered.add(merit);
        continue;
      }
      Integer freeCount = freeMerits.get(meritId);
      if (freeCount > 0) {
        freeMerits.put(meritId, freeCount - 1);
      }
      else {
        meritsNotYetCovered.add(merit);
      }
    }
    return meritsNotYetCovered;
  }
}