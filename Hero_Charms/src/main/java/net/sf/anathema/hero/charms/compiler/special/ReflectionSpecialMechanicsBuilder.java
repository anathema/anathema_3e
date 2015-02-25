package net.sf.anathema.hero.charms.compiler.special;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.charm.template.special.SpecialCharmTemplate;
import net.sf.anathema.hero.charms.model.special.CharmSpecialMechanic;
import net.sf.anathema.library.initialization.ObjectFactory;

public class ReflectionSpecialMechanicsBuilder {
	private final List<CharmSpecialMechanicsBuilder> builders = new ArrayList<>();

  public ReflectionSpecialMechanicsBuilder(ObjectFactory objectFactory) {
    this.builders.addAll(objectFactory.instantiateAllImplementers(CharmSpecialMechanicsBuilder.class));
  }

  public List<CharmSpecialMechanic> readCharmMechanics(SpecialCharmTemplate overallDto, String id) {
  	List<CharmSpecialMechanic> mechanics = new ArrayList<>();
  	for (CharmSpecialMechanicsBuilder builder : builders) {
  		if (builder.supports(overallDto)) {
  			mechanics.add(builder.readCharm(overallDto, id));
  		}
  	}
    return mechanics;
  }
}
