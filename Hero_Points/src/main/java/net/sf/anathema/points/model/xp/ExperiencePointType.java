package net.sf.anathema.points.model.xp;

import net.sf.anathema.library.identifier.Identifier;

public interface ExperiencePointType extends Identifier {
	boolean supportsExpenditureType(ExperienceExpenditureType expenditure);
}
