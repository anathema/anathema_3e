package net.sf.anathema.hero.individual.persistence.values;

import net.sf.anathema.library.initialization.ObjectFactory;
import net.sf.anathema.platform.persistence.common.ValueTemplate;

import java.util.ArrayList;
import java.util.List;

public class ReflectionValueBuilder implements ValueFactory {
	private final List<ValueBuilder> builders = new ArrayList<>();

  public ReflectionValueBuilder(ObjectFactory objectFactory) {
    this.builders.addAll(objectFactory.instantiateAllImplementers(ValueBuilder.class));
  }

  public Value getValueForTemplate(ValueTemplate template) {
    return findBuilder(template).getValueForTemplate(template);
  }

  private ValueBuilder findBuilder(ValueTemplate dto) {
    for (ValueBuilder builder : builders) {
      if (builder.supportsTemplate(dto)) {
        return builder;
      }
    }
    return new NullValueBuilder();
  }
}
