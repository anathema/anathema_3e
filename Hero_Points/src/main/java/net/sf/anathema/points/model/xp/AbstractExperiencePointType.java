package net.sf.anathema.points.model.xp;

import net.sf.anathema.library.identifier.SimpleIdentifier;

public abstract class AbstractExperiencePointType extends SimpleIdentifier implements ExperiencePointType {

	protected AbstractExperiencePointType(String id) {
		super(id);
	}

}
