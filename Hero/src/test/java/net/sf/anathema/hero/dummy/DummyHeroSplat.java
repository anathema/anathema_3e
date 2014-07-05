package net.sf.anathema.hero.dummy;

import net.sf.anathema.hero.environment.template.SplatTypeImpl;
import net.sf.anathema.hero.individual.splat.ConfiguredModel;
import net.sf.anathema.hero.individual.splat.HeroSplat;
import net.sf.anathema.hero.individual.splat.HeroType;
import net.sf.anathema.hero.individual.splat.SplatType;
import net.sf.anathema.library.identifier.SimpleIdentifier;

import java.util.ArrayList;
import java.util.List;

public class DummyHeroSplat implements HeroSplat {

  public SplatType type = new SplatTypeImpl(new DummyMundaneHeroType(), new SimpleIdentifier("Test"));

  @Override
  public SplatType getTemplateType() {
    return type;
  }

  @Override
  public List<ConfiguredModel> getModels() {
    return new ArrayList<>();
  }

  public void setCharacterType(HeroType heroType) {
    this.type = new SplatTypeImpl(heroType, type.getSubType());
  }

  public void setSubType(String subtype) {
    this.type = new SplatTypeImpl(type.getHeroType(), new SimpleIdentifier(subtype));
  }
}