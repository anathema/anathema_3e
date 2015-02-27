package net.sf.anathema.hero.thaumaturgy.compiler.json.template;


public class RitualTemplate {
	public String name;
	public boolean basicRitual = false;
	public boolean advancedRitual = false;
	
	public RitualTemplate() { }
	
	public RitualTemplate(String name, boolean basic, boolean advanced) {
		this.name = name;
		this.basicRitual = basic;
		this.advancedRitual = advanced;
	}
}