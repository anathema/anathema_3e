package net.sf.anathema.hero.merits.compiler.json.template;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.hero.merits.compiler.json.template.requirements.MeritRequirementsTemplate;
import net.sf.anathema.hero.merits.compiler.template.mechanics.MeritMechanicalDetailTemplate;

public class MeritTemplate {
	public String name;
	public String values;
	public String type;
	public boolean repurchases;
	public List<MeritRequirementsTemplate> requirements = new ArrayList<>();
	public List<MeritMechanicalDetailTemplate> mechanics = new ArrayList<>();
	public List<String> suggestions = new ArrayList<>();
}