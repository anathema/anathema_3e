package net.sf.anathema.hero.application.creation;

import net.sf.anathema.hero.individual.splat.HeroSplat;
import net.sf.anathema.hero.individual.splat.HeroType;
import net.sf.anathema.library.interaction.model.Tool;

import java.util.Collections;
import java.util.List;

public class HeroCreationPresenter {

  private final CharacterCreationView view;
  private final HeroCreationPageProperties properties;
  private final ICharacterItemCreationModel model;
  private IItemOperator operator;

  public HeroCreationPresenter(CharacterCreationView view, HeroCreationPageProperties properties,
                               ICharacterItemCreationModel model, IItemOperator operator) {
    this.view = view;
    this.properties = properties;
    this.model = model;
    this.operator = operator;
  }

  public void initPresentation() {
    ButtonPanel panel = view.addToggleButtonPanel();
    List<HeroType> heroTypes = model.getAvailableHeroTypes();
    for (HeroType type : heroTypes) {
      List<HeroSplat> availableTemplates = model.getAvailableTemplates(type);
      Collections.sort(availableTemplates,
              (o1, o2) -> getTemplateResource(o1).compareTo(getTemplateResource(o2)));
      for (HeroSplat splat : availableTemplates) {
        Tool button = panel.addButton(properties.getTemplateUI().getLabel(splat));
        button.setIcon(properties.getTypeIcon(type));
        button.setCommand(() -> {
          view.close();
          operator.operate(splat);
        });
      }
    }
    view.show();
  }

  private String getTemplateResource(HeroSplat o1) {
    return properties.getTemplateUI().getLabel(o1);
  }
}