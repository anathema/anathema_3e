package net.sf.anathema.hero.charms.compiler.special;

import net.sf.anathema.magic.template.special.SpecialCharmTemplate;
import net.sf.anathema.hero.charms.compiler.special.learning.NullSpecialCharmBuilder;
import net.sf.anathema.hero.charms.model.special.CharmSpecialLearning;
import net.sf.anathema.library.initialization.ObjectFactory;

import java.util.ArrayList;
import java.util.List;

public class ReflectionSpecialCharmBuilder {

  private final List<CharmSpecialLearningBuilder> builders = new ArrayList<>();

  public ReflectionSpecialCharmBuilder(ObjectFactory objectFactory) {
    this.builders.addAll(objectFactory.instantiateAllImplementers(CharmSpecialLearningBuilder.class));
  }

  public CharmSpecialLearning readCharmLearning(SpecialCharmTemplate overallDto,
  		AdditionalCharmFactory factory,
  		ExistingMechanicTemplateSupplier supplier) {
    return findBuilder(overallDto).readCharm(overallDto, factory, supplier);
  }

  private CharmSpecialLearningBuilder findBuilder(SpecialCharmTemplate dto) {
    for (CharmSpecialLearningBuilder builder : builders) {
      if (builder.supports(dto)) {
        return builder;
      }
    }
    return new NullSpecialCharmBuilder();
  }
}