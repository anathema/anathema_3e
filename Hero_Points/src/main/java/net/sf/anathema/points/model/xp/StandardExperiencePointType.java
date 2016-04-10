package net.sf.anathema.points.model.xp;

public class StandardExperiencePointType extends AbstractExperiencePointType {

	public static final StandardExperiencePointType Type = new StandardExperiencePointType();
	
	private static final String Id = "XP";
	
	public StandardExperiencePointType() {
		super(Id);
	}

	@Override
	public boolean supportsExpenditureType(ExperienceExpenditureType expenditure) {
		return true;
	}
}
