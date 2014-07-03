package net.sf.anathema.hero.charms.compiler.special;

import net.sf.anathema.charm.data.reference.CharmName;
import net.sf.anathema.charm.parser.template.special.SpecialCharmTemplate;
import net.sf.anathema.hero.charms.model.special.ISpecialCharm;
import net.sf.anathema.hero.charms.model.special.prerequisite.PrerequisiteModifyingCharm;
import net.sf.anathema.hero.traits.TraitTypeFinder;
import net.sf.anathema.hero.traits.model.TraitType;

@SuppressWarnings("UnusedDeclaration")
public class TranscendenceCharmBuilder implements SpecialCharmBuilder {

  private final TraitTypeFinder traitTypeFinder = new TraitTypeFinder();

  @Override
  public ISpecialCharm readCharm(SpecialCharmTemplate overallDto) {
    TraitType trait = traitTypeFinder.getTrait(overallDto.transcendence.trait);
    int modifier = overallDto.transcendence.modifier;
    return new PrerequisiteModifyingCharm(new CharmName(overallDto.charmId), trait, modifier);
  }

  @Override
  public boolean supports(SpecialCharmTemplate overallDto) {
    return overallDto.transcendence != null;
  }
}