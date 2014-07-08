package net.sf.anathema.hero.charms.model;

import net.sf.anathema.library.sort.WeightedObject;
import net.sf.anathema.library.sort.WeightedObjectSorter;
import net.sf.anathema.magic.data.Magic;

public class WeightedMagicSorter extends WeightedObjectSorter<Magic> {

  @Override
  public WeightedObject<Magic> createWeightedObject(Magic magic, int weight) {
    return new WeightedMagic(magic, weight);
  }

}