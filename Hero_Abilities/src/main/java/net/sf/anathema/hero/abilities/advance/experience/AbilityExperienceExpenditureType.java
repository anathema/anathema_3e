package net.sf.anathema.hero.abilities.advance.experience;

import net.sf.anathema.hero.abilities.model.AbilitiesModel;
import net.sf.anathema.library.identifier.SimpleIdentifier;
import net.sf.anathema.points.model.xp.ExperienceExpenditureType;

public class AbilityExperienceExpenditureType extends SimpleIdentifier implements ExperienceExpenditureType {

	public static final AbilityExperienceExpenditureType Type = new AbilityExperienceExpenditureType();
	
	private static final String ID = AbilitiesModel.ID.toString();
	
	public AbilityExperienceExpenditureType() {
		super(ID);
	}
}
