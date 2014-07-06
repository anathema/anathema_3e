package net.sf.anathema.hero.application.item;

import net.sf.anathema.hero.application.CharacterChangeManagement;
import net.sf.anathema.hero.application.creation.DefaultHero;
import net.sf.anathema.hero.application.creation.models.HeroModelInitializer;
import net.sf.anathema.hero.environment.HeroEnvironment;
import net.sf.anathema.hero.individual.change.ChangeAnnouncer;
import net.sf.anathema.hero.individual.model.HeroModel;
import net.sf.anathema.hero.individual.splat.HeroSplat;
import net.sf.anathema.library.identifier.Identifier;
import net.sf.anathema.platform.repository.ChangeManagement;

import java.util.Iterator;

public class HeroItemDataImp implements HeroItemData {

  private final CharacterChangeManagement management = new CharacterChangeManagement(this);
  private final DefaultHero hero;

  public HeroItemDataImp(HeroSplat template, HeroEnvironment environment) {
    this.hero = new DefaultHero(template);
    addModels(environment);
    management.initListening();
  }

  private void addModels(HeroEnvironment environment) {
    HeroModelInitializer initializer = new HeroModelInitializer(environment, getSplat());
    initializer.addModels(hero);
  }

  @Override
  public ChangeManagement getChangeManagement() {
    return management;
  }

  @Override
  public HeroSplat getSplat() {
    return hero.getSplat();
  }

  @Override
  public ChangeAnnouncer getChangeAnnouncer() {
    return hero.getChangeAnnouncer();
  }

  @Override
  public <M extends HeroModel> M getModel(Identifier id) {
    return hero.getModel(id);
  }

  public void markReadyForWork() {
    hero.setFullyLoaded(true);
  }

  @Override
  public boolean isFullyLoaded() {
    return hero.isFullyLoaded();
  }

  @Override
  public Iterator<HeroModel> iterator() {
    return hero.iterator();
  }
}