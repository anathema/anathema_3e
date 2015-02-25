package net.sf.anathema.charm.template.special;

import net.sf.anathema.charm.template.special.learning.MultiEffect;
import net.sf.anathema.charm.template.special.learning.Repurchase;
import net.sf.anathema.charm.template.special.learning.SubEffect;
import net.sf.anathema.charm.template.special.learning.Upgradable;
import net.sf.anathema.charm.template.special.mechanics.AddsHealthLevelsByTraitTemplate;
import net.sf.anathema.charm.template.special.mechanics.ThaumaturgyTemplate;

public class SpecialCharmTemplate {
	
	public String charmId;
  
	// Learning
  public Repurchase repurchase;
  public MultiEffect multiEffect;
  public Upgradable upgradable;
  public SubEffect subEffect;
  
  // Mechanics
  public AddsHealthLevelsByTraitTemplate addsHealthLevelsByTrait;
  public ThaumaturgyTemplate thaumaturgy;
}
