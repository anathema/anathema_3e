package net.sf.anathema.hero.equipment.model.natural;

import net.sf.anathema.equipment.stats.impl.WeaponStats;
import net.sf.anathema.equipment.stats.impl.WeaponTag;
import net.sf.anathema.library.identifier.Identifier;
import net.sf.anathema.library.identifier.SimpleIdentifier;

public class Grapple extends WeaponStats {

  public Grapple() {
    addTag(WeaponTag.Natural);
    addTag(WeaponTag.Light);
    addTag(WeaponTag.Bashing);
  }

  @Override
  public int getAccuracy() {
    return 0;
  }

  @Override
  public Identifier getName() {
    return new SimpleIdentifier("Grapple");
  }
}