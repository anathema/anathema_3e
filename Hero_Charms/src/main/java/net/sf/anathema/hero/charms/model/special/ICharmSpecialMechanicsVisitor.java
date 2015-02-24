package net.sf.anathema.hero.charms.model.special;

import net.sf.anathema.hero.charms.model.special.mechanics.IAddsHealthLevelsByTraitMechanic;


public interface ICharmSpecialMechanicsVisitor {
 void visitAddsHealthLevelsByTraitCharm(IAddsHealthLevelsByTraitMechanic mechanic);
}
