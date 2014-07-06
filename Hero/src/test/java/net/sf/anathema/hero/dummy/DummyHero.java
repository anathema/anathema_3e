package net.sf.anathema.hero.dummy;

import net.sf.anathema.hero.individual.change.ChangeAnnouncer;
import net.sf.anathema.hero.individual.change.ChangeAnnouncerImpl;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.individual.model.HeroModel;
import net.sf.anathema.hero.individual.splat.HeroSplat;
import net.sf.anathema.library.identifier.Identifier;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class DummyHero implements Hero {

  private final ChangeAnnouncer changeAnnouncer = new ChangeAnnouncerImpl();

  public final DummyHeroSplat template = new DummyHeroSplat();
  public final Map<Identifier, HeroModel> modelsById = new HashMap<>();


  public void addModel(HeroModel model) {
    modelsById.put(model.getId(), model);
  }

  @Override
  public HeroSplat getSplat() {
    return template;
  }

  @Override
  public ChangeAnnouncer getChangeAnnouncer() {
    return changeAnnouncer;
  }

  @Override
  public <M extends HeroModel> M getModel(Identifier id) {
    return (M) modelsById.get(id);
  }

  @Override
  public boolean isFullyLoaded() {
    return true;
  }

  @Override
  public Iterator<HeroModel> iterator() {
    return modelsById.values().iterator();
  }
}
