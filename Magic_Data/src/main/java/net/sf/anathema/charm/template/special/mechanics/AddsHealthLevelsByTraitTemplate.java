package net.sf.anathema.charm.template.special.mechanics;

import java.util.HashMap;
import java.util.Map;

import net.sf.anathema.charm.template.special.ISpecialCharmMechanicsTemplate;

public class AddsHealthLevelsByTraitTemplate implements ISpecialCharmMechanicsTemplate {

  public String trait;
  public Map<Integer, String[]> grantedLevels = new HashMap<>();
}
