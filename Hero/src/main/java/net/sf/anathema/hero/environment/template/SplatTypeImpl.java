package net.sf.anathema.hero.environment.template;

import net.sf.anathema.hero.individual.splat.HeroType;
import net.sf.anathema.hero.individual.splat.SplatType;
import net.sf.anathema.library.identifier.Identifier;
import org.apache.commons.lang3.builder.EqualsBuilder;

public final class SplatTypeImpl implements SplatType {

  private final HeroType heroType;
  private final Identifier subType;

  public SplatTypeImpl(HeroType heroType, Identifier subType) {
    this.heroType = heroType;
    this.subType = subType;
  }

  @Override
  public HeroType getHeroType() {
    return heroType;
  }

  @Override
  public Identifier getSubType() {
    return subType;
  }

  @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
  @Override
  public boolean equals(Object obj) {
    return EqualsBuilder.reflectionEquals(this, obj);
  }
  
  @Override
  public String toString() {
	  return heroType.getId() + ", " + subType.getId();
  }

  @Override
  public int hashCode() {
    return heroType.getId().hashCode();
  }
}