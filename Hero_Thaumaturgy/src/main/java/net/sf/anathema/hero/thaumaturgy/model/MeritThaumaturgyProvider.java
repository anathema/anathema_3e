package net.sf.anathema.hero.thaumaturgy.model;

import net.sf.anathema.hero.merits.model.MechanicalDetailReference;
import net.sf.anathema.hero.merits.model.Merit;
import net.sf.anathema.hero.merits.model.MeritsModel;

public class MeritThaumaturgyProvider implements ThaumaturgyProvider {
  private final MeritsModel merits;

  public MeritThaumaturgyProvider(MeritsModel merits) {
    this.merits = merits;
  }

  @Override
  public boolean grantsThaumaturgy() {
    for (Merit merit : merits.getPossessedEntries()) {
      if (merit.hasMechanicalDetail(new MechanicalDetailReference("GrantsThaumaturgy"))) {
        return true;
      }
    }
    return false;
  }

  @Override
  public int numberOfRitualsProvided() {
    return 0;
  }
}