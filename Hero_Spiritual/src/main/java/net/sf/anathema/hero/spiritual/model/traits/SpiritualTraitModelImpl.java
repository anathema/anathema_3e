package net.sf.anathema.hero.spiritual.model.traits;

import net.sf.anathema.hero.framework.HeroEnvironment;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.model.HeroModel;
import net.sf.anathema.hero.model.change.ChangeAnnouncer;
import net.sf.anathema.hero.spiritual.template.SpiritualTraitsTemplate;
import net.sf.anathema.hero.traits.model.DefaultTraitMap;
import net.sf.anathema.hero.traits.model.Trait;
import net.sf.anathema.hero.traits.model.TraitModel;
import net.sf.anathema.hero.traits.model.TraitModelFetcher;
import net.sf.anathema.hero.traits.model.event.TraitValueChangedListener;
import net.sf.anathema.hero.traits.model.rules.limitation.TraitLimitation;
import net.sf.anathema.hero.traits.model.types.OtherTraitType;
import net.sf.anathema.hero.traits.template.TraitTemplateMap;
import net.sf.anathema.lib.util.Identifier;

public class SpiritualTraitModelImpl extends DefaultTraitMap implements SpiritualTraitModel, HeroModel {

  private SpiritualTraitsTemplate template;
  private TraitModel traitModel;

  public SpiritualTraitModelImpl(SpiritualTraitsTemplate template) {
    this.template = template;
  }

  @Override
  public Identifier getId() {
    return ID;
  }

  @Override
  public void initialize(HeroEnvironment environment, Hero hero) {
    addEssence(hero);
    addWillpower(hero);
    this.traitModel = TraitModelFetcher.fetch(hero);
    getTrait(OtherTraitType.Essence).addCurrentValueListener(new EssenceLimitationListener(traitModel, hero));
    traitModel.addTraits(getAll());
  }

  @Override
  public void initializeListening(ChangeAnnouncer announcer) {
    for (Trait trait : getAll()) {
      trait.addCurrentValueListener(new TraitValueChangedListener(announcer, trait));
    }
  }

  private void addEssence(Hero hero) {
    SpiritualTraitFactory traitFactory = createTraitFactory(hero);
    addTraits(traitFactory.createTrait(OtherTraitType.Essence));
  }

  private void addWillpower(Hero hero) {
    SpiritualTraitFactory traitFactory = createTraitFactory(hero);
    addTraits(traitFactory.createTrait(OtherTraitType.Willpower));
  }

  private SpiritualTraitFactory createTraitFactory(Hero hero) {
    TraitTemplateMap map = new SpiritualTraitTemplateMap(template);
    return new SpiritualTraitFactory(hero, map);
  }

  @Override
  public int getEssenceCap(boolean modified) {
    Trait essence = getTrait(OtherTraitType.Essence);
    return modified ? essence.getModifiedMaximalValue() : essence.getUnmodifiedMaximalValue();
  }

  @Override
  public TraitLimitation getEssenceLimitation() {
    return traitModel.createLimitation(template.essence.limitation);
  }
}
