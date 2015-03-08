package net.sf.anathema.hero.health.model;

import net.sf.anathema.magic.data.Duration;
import net.sf.anathema.library.resources.Resources;

import com.google.common.collect.ImmutableMap;

import java.util.HashMap;
import java.util.Map;

public class HealingDataTable {
	
	private Resources resources;
	
	public HealingDataTable(Resources resources) {
		this.resources = resources;
	}
	
	private Map<HealthLevelType, Map<HealthType, Duration>> forExaltedHealing()
	{
		Map<HealthLevelType, Map<HealthType, Duration>> chart = new HashMap<>();
		chart.put(HealthLevelType.ZERO,
				ImmutableMap.of(
						HealthType.Bashing,
						new Duration(getUnitString(1, "hour")),
						HealthType.Lethal,
						new Duration(getUnitString(1, "day"))));
		chart.put(HealthLevelType.ONE,
				ImmutableMap.of(
						HealthType.Bashing,
						new Duration(getUnitString(12, "hour")),
						HealthType.Lethal,
						new Duration(getUnitString(2, "day"))));
		chart.put(HealthLevelType.TWO,
				ImmutableMap.of(
						HealthType.Bashing,
						new Duration(getUnitString(1, "day")),
						HealthType.Lethal,
						new Duration(getUnitString(3, "day"))));
		chart.put(HealthLevelType.FOUR,
				ImmutableMap.of(
						HealthType.Bashing,
						new Duration(getUnitString(2, "day")),
						HealthType.Lethal,
						new Duration(getUnitString(5, "day"))));
		return chart;
	}
	
	private Map<HealthLevelType, Map<HealthType, Duration>> forMundaneHealing()
	{
		Map<HealthLevelType, Map<HealthType, Duration>> chart = new HashMap<>();
		chart.put(HealthLevelType.ZERO,
				ImmutableMap.of(
						HealthType.Bashing,
						new Duration(getUnitString(12, "hour")),
						HealthType.Lethal,
						new Duration(getUnitString(2, "day"))));
		chart.put(HealthLevelType.ONE,
				ImmutableMap.of(
						HealthType.Bashing,
						new Duration(getUnitString(1, "day")),
						HealthType.Lethal,
						new Duration(getUnitString(4, "day"))));
		chart.put(HealthLevelType.TWO,
				ImmutableMap.of(
						HealthType.Bashing,
						new Duration(getUnitString(4, "day")),
						HealthType.Lethal,
						new Duration(getUnitString(1, "week"))));
		chart.put(HealthLevelType.FOUR,
				ImmutableMap.of(
						HealthType.Bashing,
						new Duration(getUnitString(1, "week")),
						HealthType.Lethal,
						new Duration(getUnitString(1, "month"))));
		return chart;
	}
	
	private String getUnitString(int quantity, String type) {
		return quantity + " " +
				resources.getString("Charm.Unit." + type + "." +
						(quantity > 1 ? "Plural" : "Singular"));
	}

	Duration getHealingTime(boolean isFastHealing, 
			HealthLevelType level, HealthType damage) {
		if (damage == HealthType.Aggravated) {
			damage = HealthType.Lethal;
		}
		if (level == HealthLevelType.INCAPACITATED) {
			// TODO: Storyteller discretion
			return null;
		}
		
		if (isFastHealing) {
			return forExaltedHealing().get(level).get(damage);
		} else {
			return forMundaneHealing().get(level).get(damage);
		}
		
	}
}
