package net.sf.anathema.hero.charms.compiler.special;

import net.sf.anathema.charm.template.special.SpecialCharmTemplate;
import net.sf.anathema.hero.charms.model.special.CharmSpecialMechanic;
import net.sf.anathema.hero.individual.persistence.values.ValueFactory;
import net.sf.anathema.library.initialization.ObjectFactory;

import java.util.ArrayList;
import java.util.List;

public class ReflectionSpecialMechanicsBuilder {
	private final List<CharmSpecialMechanicsBuilder> builders = new ArrayList<>();

  public ReflectionSpecialMechanicsBuilder(ObjectFactory objectFactory) {
    this.builders.addAll(objectFactory.instantiateAllImplementers(CharmSpecialMechanicsBuilder.class));
  }

  public List<CharmSpecialMechanic> readCharmMechanics(SpecialCharmTemplate overallDto, String id, ValueFactory valueFactory) {
  	List<CharmSpecialMechanic> mechanics = new ArrayList<>();
  	for (CharmSpecialMechanicsBuilder builder : builders) {
  		if (builder.supports(overallDto)) {
  			mechanics.add(builder.readCharm(overallDto, id, valueFactory));
  		}
  	}
    return mechanics;
  }
}
