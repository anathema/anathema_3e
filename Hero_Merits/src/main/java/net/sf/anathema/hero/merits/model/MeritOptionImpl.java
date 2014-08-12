package net.sf.anathema.hero.merits.model;

import net.sf.anathema.hero.merits.compiler.json.template.MeritTemplate;

public class MeritOptionImpl implements MeritOption {
	
	private final String name;
	private final MeritCategory type;
	private final boolean allowRepurchase;
	private final boolean[] legalValues = new boolean[MAX_MERIT_RATING + 1];
	
	public MeritOptionImpl(MeritTemplate template) {
		this.name = template.name;
		this.allowRepurchase = template.repurchases;
		this.type = MeritCategory.valueOf(template.type);
		
		parseLegalValues(template.values);
	}
	
	private void parseLegalValues(String values) {
		for (String value : values.split(",")) {
			value = value.trim();
			if (value.contains("-")) {
				String[] range = value.split("-");
				int start = Integer.parseInt(range[0]);
				int end = Integer.parseInt(range[1]);
				
				assert(start >= 0 && start <= MAX_MERIT_RATING);
				assert(end > start && end <= MAX_MERIT_RATING);
				
				for (int i = start; i <= end; i++) {
					legalValues[i] = true;
				}
				continue;
			}
			int position = Integer.parseInt(value);
			assert (position >= 0 && position <= MAX_MERIT_RATING);
			legalValues[position] = true;
		}
	}

	@Override
	public String getId() {
		return name;
	}
	
	@Override
	public MeritCategory getType() {
		return type;
	}

	@Override
	public boolean allowsRepurchase() {
		return allowRepurchase;
	}
	
	
}
