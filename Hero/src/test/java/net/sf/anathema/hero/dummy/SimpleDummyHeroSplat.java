package net.sf.anathema.hero.dummy;

import net.sf.anathema.hero.environment.template.SplatTypeImpl;
import net.sf.anathema.hero.individual.splat.ConfiguredModel;
import net.sf.anathema.hero.individual.splat.HeroSplat;
import net.sf.anathema.hero.individual.splat.HeroType;
import net.sf.anathema.hero.individual.splat.SplatType;
import net.sf.anathema.library.identifier.SimpleIdentifier;

import java.util.ArrayList;
import java.util.List;

public class SimpleDummyHeroSplat implements HeroSplat {

  private final String subtype;
  private final HeroType type;

  public SimpleDummyHeroSplat(HeroType type, String subtype) {
    this.type = type;
    this.subtype = subtype;
  }

  @Override
  public SplatType getTemplateType() {
    if (subtype == null) {
      return new SplatTypeImpl(type, new SimpleIdentifier("Test"));
    }
    return new SplatTypeImpl(type, new SimpleIdentifier(subtype));
  }

  @Override
  public List<ConfiguredModel> getModels() {
    return new ArrayList<>();
  }
}