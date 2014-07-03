package net.sf.anathema.hero.charms.compiler.special;

import net.sf.anathema.charm.parser.template.special.SpecialCharmTemplate;
import net.sf.anathema.framework.environment.dependencies.DoNotInstantiateAutomatically;
import net.sf.anathema.hero.charms.model.special.ISpecialCharm;

@DoNotInstantiateAutomatically
public class NullSpecialCharmBuilder implements SpecialCharmBuilder {
  @Override
  public ISpecialCharm readCharm(SpecialCharmTemplate dto) {
    return null;
  }

  @Override
  public boolean supports(SpecialCharmTemplate dto) {
    return false;
  }
}
