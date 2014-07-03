package net.sf.anathema.hero.charms.compiler.special;

import net.sf.anathema.charm.data.reference.CharmName;
import net.sf.anathema.charm.template.special.SpecialCharmTemplate;
import net.sf.anathema.charm.template.special.TraitCapModifier;
import net.sf.anathema.hero.charms.model.special.ISpecialCharm;
import net.sf.anathema.hero.charms.model.special.traitcap.TraitCapModifyingCharm;
import net.sf.anathema.hero.traits.TraitTypeFinder;

@SuppressWarnings("UnusedDeclaration")
public class TraitCapModifierCharmBuilder implements SpecialCharmBuilder {

  private final TraitTypeFinder traitTypeFinder = new TraitTypeFinder();

  @Override
  public ISpecialCharm readCharm(SpecialCharmTemplate overallDto) {
    TraitCapModifier dto = overallDto.traitCapModifier;
    return new TraitCapModifyingCharm(new CharmName(overallDto.charmId), traitTypeFinder.getTrait(dto.trait), dto.modifier);
  }

  @Override
  public boolean supports(SpecialCharmTemplate overallDto) {
    return overallDto.traitCapModifier != null;
  }
}