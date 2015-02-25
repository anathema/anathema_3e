package net.sf.anathema.hero.traits.persistence.values;

import net.sf.anathema.platform.persistence.JsonType;
import net.sf.anathema.platform.persistence.common.ValueTemplate;

@JsonType("Trait")
public class TraitValueTemplate implements ValueTemplate {
	public String trait;
}
