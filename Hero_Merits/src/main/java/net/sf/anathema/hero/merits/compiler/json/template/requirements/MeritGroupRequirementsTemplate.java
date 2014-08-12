package net.sf.anathema.hero.merits.compiler.json.template.requirements;

import java.util.ArrayList;
import java.util.List;

public class MeritGroupRequirementsTemplate implements MeritRequirementsTemplate {
	public static final String jsonLabel = "group";
	
	public List<MeritRequirementsTemplate> requirements = new ArrayList<>();
}
