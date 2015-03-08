package net.sf.anathema.hero.charms.compiler.special;

import net.sf.anathema.magic.template.special.SpecialCharmTemplate;
import net.sf.anathema.hero.charms.model.special.CharmSpecialLearning;
import net.sf.anathema.library.initialization.DoNotInstantiateAutomatically;

@DoNotInstantiateAutomatically
public class ConfigurableDummySpecialCharmBuilder implements CharmSpecialLearningBuilder {
  private SpecialCharmTemplate dto;
  private CharmSpecialLearning charm;

  @Override
  public CharmSpecialLearning readCharm(SpecialCharmTemplate dto,
  		AdditionalCharmFactory factory,
  		ExistingMechanicTemplateSupplier supplier) {
    if (dto.equals(this.dto)){
      return charm;
    }
    throw new RuntimeException();
  }

  @Override
  public boolean supports(SpecialCharmTemplate dto) {
    return dto.equals(this.dto);
  }

  public ConfigurableDummySpecialCharmBuilder support(SpecialCharmTemplate dto) {
    this.dto = dto;
    return this;
  }

  public ConfigurableDummySpecialCharmBuilder with(CharmSpecialLearning charm) {
    this.charm = charm;
    return this;
  }
}