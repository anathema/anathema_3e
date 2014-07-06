package net.sf.anathema.hero.application.perspective;

import com.google.common.base.Objects;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.sf.anathema.hero.application.HeroSplatHolder;
import net.sf.anathema.hero.application.creation.ICharacterItemCreationModel;
import net.sf.anathema.hero.environment.HeroEnvironment;
import net.sf.anathema.hero.environment.herotype.HeroTypes;
import net.sf.anathema.hero.environment.template.TemplateRegistry;
import net.sf.anathema.hero.individual.splat.HeroSplat;
import net.sf.anathema.hero.individual.splat.HeroType;
import net.sf.anathema.library.event.ChangeListener;
import org.jmock.example.announcer.Announcer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CharacterItemCreationModel implements ICharacterItemCreationModel {

  private final Announcer<ChangeListener> control = Announcer.to(ChangeListener.class);
  private final Multimap<HeroType, HeroSplat> templatesByType = HashMultimap.create();
  private final List<HeroType> availableHeroTypes = new ArrayList<>();
  private final HeroEnvironment generics;
  private final HeroSplatHolder templateHolder = new HeroSplatHolder();
  private HeroType selectedType;

  public CharacterItemCreationModel(HeroEnvironment generics) {
    this.generics = generics;
    initializeTypesAndTemplates();
    setCharacterType(availableHeroTypes.get(0));
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
  public Iterable<HeroType> getAvailableHeroTypes() {
    return availableHeroTypes;
  }

  @Override
  public void setCharacterType(HeroType type) {
    if (Objects.equal(selectedType, type)) {
      return;
    }
    this.selectedType = type;
    setTemplateToDefault();
    control.announce().changeOccurred();
  }

  private void setTemplateToDefault() {
    Collection<HeroSplat> templates = generics.getTemplateRegistry().getAllSupportedTemplates(selectedType);
    HeroSplat defaultTemplate = templates.iterator().next();
    templateHolder.setSplat(defaultTemplate);
  }

  @Override
  public List<HeroSplat> getAvailableTemplates() {
    Collection<HeroSplat> list = templatesByType.get(selectedType);
    return new ArrayList<>(list);
  }

  @Override
  public void setSelectedTemplate(HeroSplat newValue) {
    if (templateHolder.isCurrentlySelected(newValue)) {
      return;
    }
    templateHolder.setSplat(newValue);
    control.announce().changeOccurred();
  }

  @Override
  public HeroSplat getSelectedTemplate() {
    return templateHolder.getSplat();
  }

  @Override
  public void addListener(ChangeListener listener) {
    control.addListener(listener);
  }
}