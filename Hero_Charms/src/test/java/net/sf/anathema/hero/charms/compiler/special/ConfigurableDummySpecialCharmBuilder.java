package net.sf.anathema.hero.charms.compiler.special;

import net.sf.anathema.charm.template.special.SpecialCharmTemplate;
import net.sf.anathema.framework.environment.dependencies.DoNotInstantiateAutomatically;
import net.sf.anathema.hero.charms.model.special.ISpecialCharm;

@DoNotInstantiateAutomatically
public class ConfigurableDummySpecialCharmBuilder implements SpecialCharmBuilder {
  private SpecialCharmTemplate dto;
  private ISpecialCharm charm;

  @Override
  public ISpecialCharm readCharm(SpecialCharmTemplate dto) {
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

  public ConfigurableDummySpecialCharmBuilder with(ISpecialCharm charm) {
    this.charm = charm;
    return this;
  }
}