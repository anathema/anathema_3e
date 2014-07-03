package net.sf.anathema.hero.charms.compiler.special;

import net.sf.anathema.charm.data.reference.CharmName;
import net.sf.anathema.charm.parser.template.special.SpecialCharmTemplate;
import net.sf.anathema.hero.charms.model.special.ISpecialCharm;
import net.sf.anathema.hero.charms.model.special.paintolerance.StaticPainToleranceCharm;

@SuppressWarnings("UnusedDeclaration")
public class PainToleranceCharmBuilder implements SpecialCharmBuilder {

  public ISpecialCharm readCharm(SpecialCharmTemplate overallDto) {
    return new StaticPainToleranceCharm(new CharmName(overallDto.charmId), overallDto.painTolerance);
  }

  @Override
  public boolean supports(SpecialCharmTemplate overallDto) {
    return overallDto.painTolerance != null;
  }
}