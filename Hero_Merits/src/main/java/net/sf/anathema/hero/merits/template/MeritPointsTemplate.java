package net.sf.anathema.hero.merits.template;

import net.sf.anathema.hero.traits.template.cost.NewRatingCostTemplate;

import java.util.HashMap;
import java.util.Map;

public class MeritPointsTemplate {
	public int freebiePoints = 0;
	public int bonusPoints = 0;
	public NewRatingCostTemplate experiencePoints = new NewRatingCostTemplate();
    public Map<String, Integer> freeMerits = new HashMap<>();
}
