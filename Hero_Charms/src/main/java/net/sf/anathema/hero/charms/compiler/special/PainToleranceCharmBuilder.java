package net.sf.anathema.hero.charms.compiler.special;

import net.sf.anathema.charm.data.reference.CharmName;
import net.sf.anathema.hero.charms.model.special.ISpecialCharm;
import net.sf.anathema.hero.charms.model.special.paintolerance.StaticPainToleranceCharm;
import net.sf.anathema.hero.magic.parser.dto.special.SpecialCharmDto;

@SuppressWarnings("UnusedDeclaration")
public class PainToleranceCharmBuilder implements SpecialCharmBuilder {

  public ISpecialCharm readCharm(SpecialCharmDto overallDto) {
    return new StaticPainToleranceCharm(new CharmName(overallDto.charmId), overallDto.painTolerance);
  }

  @Override
  public boolean supports(SpecialCharmDto overallDto) {
    return overallDto.painTolerance != null;
  }
}