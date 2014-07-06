package net.sf.anathema.hero.application.creation;

import net.sf.anathema.hero.individual.splat.HeroSplat;
import net.sf.anathema.hero.individual.splat.HeroType;
import net.sf.anathema.library.interaction.model.ToggleTool;
import net.sf.anathema.library.interaction.model.Tool;
import net.sf.anathema.library.view.VetoableObjectSelectionView;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
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
    view.setTitle(properties.getTitle());
    ToggleButtonPanel panel = view.addToggleButtonPanel();
    for (final HeroType type : model.getAvailableHeroTypes()) {
      final ToggleTool button = panel.addButton(properties.getTypeString(type));
      button.setIcon(properties.getTypeIcon(type));
      button.setCommand(() -> model.setCharacterType(type));
      model.addListener(() -> updateButtonChoice(type, button));
      updateButtonChoice(type, button);
    }
    final VetoableObjectSelectionView<HeroSplat> list = view.addObjectSelectionList();
    list.setCellRenderer(properties.getTemplateUI());
    list.addObjectSelectionChangedListener(newValue -> {
      if (newValue == null) {
        return;
      }
      model.setSelectedTemplate(newValue);
    });
    initButtons();
    model.addListener(() -> refreshList(list));
    refreshList(list);
    view.show();
  }

  private void updateButtonChoice(HeroType type, ToggleTool button) {
    if (type.equals(model.getSelectedTemplate().getTemplateType().getHeroType())) {
      button.select();
    } else {
      button.deselect();
    }
  }

  private void initButtons() {
    Tool ok = view.addButton();
    ok.setText(properties.getOkButtonString());
    ok.setCommand(() -> {
      view.close();
      operator.operate(model.getSelectedTemplate());
    });
    Tool cancel = view.addButton();
    cancel.setText(properties.getCancelButtonString());
    cancel.setCommand(view::close);
  }

  protected void refreshList(VetoableObjectSelectionView<HeroSplat> list) {
    List<HeroSplat> availableTemplates = model.getAvailableTemplates();
    Collections.<HeroSplat>sort(availableTemplates, (o1, o2) -> HeroCreationPresenter.this.getTemplateResource(o1).compareTo(HeroCreationPresenter.this.getTemplateResource(o2)));
    list.setObjects(availableTemplates);
    list.setSelectedObject(model.getSelectedTemplate());
  }


  private String getTemplateResource(HeroSplat o1) {
    return properties.getTemplateUI().getLabel(o1);
  }
}