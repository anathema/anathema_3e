package net.sf.anathema.herotype.solar.model.curse;

import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.traits.model.DefaultTraitType;
import net.sf.anathema.hero.traits.model.FriendlyIncrementChecker;
import net.sf.anathema.hero.traits.model.Trait;
import net.sf.anathema.hero.traits.model.rules.LimitedTrait;
import net.sf.anathema.hero.traits.model.rules.ModificationType;
import net.sf.anathema.hero.traits.template.TraitTemplate;
import net.sf.anathema.hero.traits.template.TraitTemplateFactory;
import net.sf.anathema.lib.workflow.textualdescription.ITextualDescription;
import net.sf.anathema.lib.workflow.textualdescription.SimpleTextualDescription;

public class LimitBreakImpl implements LimitBreak {

  private Trait limitTrait;
  private final ITextualDescription name = new SimpleTextualDescription("");
  private Hero hero;

  public LimitBreakImpl(Hero hero) {
    this.hero = hero;
  }

  @Override
  public Trait getLimitTrait() {
    if (limitTrait == null) {
      DefaultTraitType traitType = new DefaultTraitType(getLimitString());
      TraitTemplate limitedTemplate = TraitTemplateFactory.createStaticLimitedTemplate(0, 10, ModificationType.Free);
      limitTrait = new LimitedTrait(hero, traitType, limitedTemplate, new FriendlyIncrementChecker());
    }
    return limitTrait;
  }

  protected String getLimitString() {
    return "VirtueFlaw.LimitTrait";
  }

  @Override
  public ITextualDescription getName() {
    return name;
  }
}