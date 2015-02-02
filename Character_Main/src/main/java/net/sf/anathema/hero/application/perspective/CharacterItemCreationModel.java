package net.sf.anathema.hero.application.perspective;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.sf.anathema.hero.application.creation.ICharacterItemCreationModel;
import net.sf.anathema.hero.environment.HeroEnvironment;
import net.sf.anathema.hero.environment.herotype.HeroTypes;
import net.sf.anathema.hero.environment.template.TemplateRegistry;
import net.sf.anathema.hero.individual.splat.HeroSplat;
import net.sf.anathema.hero.individual.splat.HeroType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CharacterItemCreationModel implements ICharacterItemCreationModel {

  private final Multimap<HeroType, HeroSplat> templatesByType = HashMultimap.create();
  private final List<HeroType> availableHeroTypes = new ArrayList<>();
  private final HeroEnvironment generics;

  public CharacterItemCreationModel(HeroEnvironment generics) {
    this.generics = generics;
    initializeTypesAndTemplates();
  }

  private void initializeTypesAndTemplates() {
    HeroTypes types = generics.getHeroTypes();
    TemplateRegistry templateRegistry = generics.getTemplateRegistry();
    for (HeroType type : types) {
      Collection<HeroSplat> templates = templateRegistry.getAllSupportedTemplates(type);
      if (!templates.isEmpty()) {
        availableHeroTypes.add(type);
        templatesByType.putAll(type, templates);
      }
    }
  }

  @Override
  public List<HeroType> getAvailableHeroTypes() {
    return new ArrayList<>(availableHeroTypes);
  }

  @Override
  public List<HeroSplat> getAvailableTemplates(HeroType type) {
    Collection<HeroSplat> list = templatesByType.get(type);
    return new ArrayList<>(list);
  }
}