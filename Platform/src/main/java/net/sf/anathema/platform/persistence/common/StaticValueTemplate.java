package net.sf.anathema.platform.persistence.common;

import net.sf.anathema.platform.persistence.JsonType;

@JsonType("Static")
public class StaticValueTemplate implements ValueTemplate {
	public int value;
}
