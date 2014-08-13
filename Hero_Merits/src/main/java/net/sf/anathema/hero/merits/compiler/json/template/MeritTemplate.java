package net.sf.anathema.hero.merits.compiler.json.template;

import java.util.List;

import net.sf.anathema.hero.merits.compiler.json.template.requirements.MeritRequirementsTemplate;

public class MeritTemplate {
	public String name;
	public String values;
	public String type;
	public boolean repurchases;
	public List<MeritRequirementsTemplate> requirements;
}
