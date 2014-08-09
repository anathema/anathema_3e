package net.sf.anathema.hero.charms.model.special.oxbody;

import net.sf.anathema.hero.traits.display.Traits;
import net.sf.anathema.hero.traits.model.Trait;

import java.util.ArrayList;
import java.util.List;

public class OxBodyTechniqueArbitratorImpl implements OxBodyTechniqueArbitrator {
  private final List<OxBodyTechniqueSpecials> oxBodyList = new ArrayList<>();
  private final Traits controllingTraits;

  public OxBodyTechniqueArbitratorImpl(Traits toughnessControllingTraits) {
    this.controllingTraits = toughnessControllingTraits;
  }

  @Override
  public void addOxBodyTechniqueConfiguration(OxBodyTechniqueSpecials oxBodyTechniqueSpecials) {
    oxBodyList.add(oxBodyTechniqueSpecials);
  }

  @Override
  public boolean isIncrementAllowed(int increment) {
    int oxBodyCount = 0;
    int maxCount = Integer.MAX_VALUE;
    for (Trait trait : controllingTraits) {
      maxCount = Math.min(maxCount, trait.getCurrentValue());
    }
    for (OxBodyTechniqueSpecials configuration : oxBodyList) {
      oxBodyCount += configuration.getCurrentLearnCount();
    }
    return oxBodyCount + increment <= maxCount;
  }
}