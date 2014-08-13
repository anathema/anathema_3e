package net.sf.anathema.hero.merits.advance;

import net.sf.anathema.hero.merits.template.MeritPointsTemplate;

public class MeritCreationData {
	private final MeritPointsTemplate template;
	
	public MeritCreationData(MeritPointsTemplate template) {
		this.template = template;
	}
	
	public int getFreebiePoints() {
		return template.freebiePoints;
	}
	
	public int getBonusPointCost() {
		return template.bonusPoints;
	}
}
