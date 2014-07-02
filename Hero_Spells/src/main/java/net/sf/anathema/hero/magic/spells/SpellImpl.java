package net.sf.anathema.hero.magic.spells;

import net.sf.anathema.charm.data.reference.SpellName;
import net.sf.anathema.charm.old.cost.CostList;
import net.sf.anathema.charm.old.source.SourceBook;
import net.sf.anathema.charm.old.source.SourceList;
import net.sf.anathema.hero.magic.basic.AbstractMagic;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.spells.model.SpellsModelFetcher;
import net.sf.anathema.hero.traits.model.TraitModelFetcher;
import net.sf.anathema.hero.traits.model.TraitType;

public class SpellImpl extends AbstractMagic implements Spell {
  private SpellName name;
  private final CircleType circleType;
  private final CostList temporaryCost;
  private SourceList source;
  private final String target;

  public SpellImpl(SpellName name, CircleType circleType, CostList temporaryCost, SourceList source, String target) {
    this.name = name;
    this.circleType = circleType;
    this.temporaryCost = temporaryCost;
    this.source = source;
    this.target = target;
  }

  @Override
  public String getTarget() {
    return target;
  }

  @Override
  public SpellName getName() {
    return name;
  }

  @Override
  public CircleType getCircleType() {
    return circleType;
  }

  @Override
  public SourceBook[] getSources() {
    return new SourceBook[]{source.getPrimarySource()};
  }

  @Override
  public SourceBook getPrimarySource() {
    return source.getPrimarySource();
  }

  @Override
  public CostList getTemporaryCost() {
    return temporaryCost;
  }

  @Override
  public boolean isFavored(Hero hero) {
    TraitType traitType = SpellsModelFetcher.fetch(hero).getFavoringTraitType();
    return TraitModelFetcher.fetch(hero).getTrait(traitType).isCasteOrFavored();
  }
}