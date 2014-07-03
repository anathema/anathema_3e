package net.sf.anathema.hero.charms.model.special.traitcap;

import net.sf.anathema.hero.charms.model.special.ISpecialCharm;
import net.sf.anathema.hero.traits.model.TraitType;

public interface ITraitCapModifyingCharm extends ISpecialCharm
{
	TraitType getTraitType();
	
	int getModifier();
}
