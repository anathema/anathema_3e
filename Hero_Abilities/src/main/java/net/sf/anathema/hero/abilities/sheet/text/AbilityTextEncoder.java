package net.sf.anathema.hero.abilities.sheet.text;

import net.sf.anathema.framework.reporting.pdf.PdfReportUtils;
import net.sf.anathema.hero.abilities.model.AbilitiesModelFetcher;
import net.sf.anathema.hero.abilities.model.AllAbilityTraitTypeList;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.traits.TraitTypeList;
import net.sf.anathema.hero.traits.model.Trait;
import net.sf.anathema.hero.traits.model.state.TraitState;
import net.sf.anathema.hero.traits.sheet.encoder.AbstractTraitTextEncoder;
import net.sf.anathema.library.resources.Resources;

public class AbilityTextEncoder extends AbstractTraitTextEncoder {

  public AbilityTextEncoder(PdfReportUtils utils, Resources resources) {
    super(utils, resources);
  }

  @Override
  protected TraitTypeList getTypes(Hero hero) {
    TraitTypeList traitTypes = new TraitTypeList();
    traitTypes.addAll(AllAbilityTraitTypeList.getInstance().getAll());
    return traitTypes;
  }

  @Override
  protected TraitState getTraitState(Hero hero, Trait trait) {
    return AbilitiesModelFetcher.fetch(hero).getState(trait);
  }

  @Override
  protected String getLabelKey() {
    return "TextDescription.Label.Abilities";
  }
}
