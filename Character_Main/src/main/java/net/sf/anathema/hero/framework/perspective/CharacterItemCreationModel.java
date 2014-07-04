package net.sf.anathema.hero.framework.perspective;

import com.google.common.base.Objects;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.sf.anathema.hero.creation.ICharacterItemCreationModel;
import net.sf.anathema.hero.framework.HeroEnvironment;
import net.sf.anathema.hero.framework.HeroTemplateHolder;
import net.sf.anathema.hero.framework.type.CharacterType;
import net.sf.anathema.hero.framework.type.CharacterTypes;
import net.sf.anathema.hero.template.HeroTemplate;
import net.sf.anathema.hero.template.TemplateRegistry;
import net.sf.anathema.lib.control.ChangeListener;
import org.jmock.example.announcer.Announcer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CharacterItemCreationModel implements ICharacterItemCreationModel {

  private final Announcer<ChangeListener> control = Announcer.to(ChangeListener.class);
  private final Multimap<CharacterType, HeroTemplate> templatesByType = HashMultimap.create();
  private final List<CharacterType> availableCharacterTypes = new ArrayList<>();
  private final HeroEnvironment generics;
  private final HeroTemplateHolder templateHolder = new HeroTemplateHolder();
  private CharacterType selectedType;

  public CharacterItemCreationModel(HeroEnvironment generics) {
    this.generics = generics;
    initializeTypesAndTemplates();
    setCharacterType(availableCharacterTypes.get(0));
  }

  private void initializeTypesAndTemplates() {
    CharacterTypes types = generics.getCharacterTypes();
    TemplateRegistry templateRegistry = generics.getTemplateRegistry();
    for (CharacterType type : types) {
      Collection<HeroTemplate> templates = templateRegistry.getAllSupportedTemplates(type);
      if (!templates.isEmpty()) {
        availableCharacterTypes.add(type);
        templatesByType.putAll(type, templates);
      }
    }
  }

  @Override
  public Iterable<CharacterType> getAvailableCharacterTypes() {
    return availableCharacterTypes;
  }

  @Override
  public void setCharacterType(CharacterType type) {
    if (Objects.equal(selectedType, type)) {
      return;
    }
    this.selectedType = type;
    setTemplateToDefault();
    control.announce().changeOccurred();
  }

  private void setTemplateToDefault() {
    Collection<HeroTemplate> templates = generics.getTemplateRegistry().getAllSupportedTemplates(selectedType);
    HeroTemplate defaultTemplate = templates.iterator().next();
    templateHolder.setTemplate(defaultTemplate);
  }

  @Override
  public HeroTemplate[] getAvailableTemplates() {
    Collection<HeroTemplate> list = templatesByType.get(selectedType);
    List<HeroTemplate> copyList = new ArrayList<>(list);
    return copyList.toArray(new HeroTemplate[copyList.size()]);
  }

  @Override
  public void setSelectedTemplate(HeroTemplate newValue) {
    if (templateHolder.isCurrentlySelected(newValue)) {
      return;
    }
    templateHolder.setTemplate(newValue);
    control.announce().changeOccurred();
  }

  @Override
  public HeroTemplate getSelectedTemplate() {
    return templateHolder.getTemplate();
  }

  @Override
  public void addListener(ChangeListener listener) {
    control.addListener(listener);
  }
}