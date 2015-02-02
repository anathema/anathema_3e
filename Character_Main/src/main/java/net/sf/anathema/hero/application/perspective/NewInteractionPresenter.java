package net.sf.anathema.hero.application.perspective;

import net.sf.anathema.hero.application.creation.CharacterTemplateCreator;
import net.sf.anathema.hero.application.perspective.model.HeroIdentifier;
import net.sf.anathema.hero.application.perspective.model.ItemSelectionModel;
import net.sf.anathema.library.interaction.model.Tool;
import net.sf.anathema.platform.environment.Environment;
import net.sf.anathema.platform.taskbar.BasicUi;

public class NewInteractionPresenter {

  private final ItemSelectionModel model;
  private final Tool interaction;
  private final Environment environment;
  private HeroesGridView view;
  private Selector<HeroIdentifier> selector;

  public NewInteractionPresenter(ItemSelectionModel model, Tool interaction, Environment environment, HeroesGridView view,
                                 Selector<HeroIdentifier> selector) {
    this.model = model;
    this.interaction = interaction;
    this.environment = environment;
    this.view = view;
    this.selector = selector;
  }

  public void initPresentation() {
    initializeAppearance();
    initializeCommand();
    model.whenNewCharacterIsAdded(character -> {
      new CharacterButtonPresenter(environment, selector, character, view).initPresentation();
      view.selectButton(character.getDescriptiveFeatures().getIdentifier());
      selector.selected(character.getDescriptiveFeatures().getIdentifier());
    });
  }

  private void initializeAppearance() {
    interaction.setTooltip(environment.getString("CharacterSystem.Tools.New.Name"));
    interaction.setText(environment.getString("CharacterSystem.Tools.New.Name"));
    interaction.setIcon(new BasicUi().getNewIconPathForTaskbar());
  }

  private void initializeCommand() {
    interaction.setCommand(() -> {
      CharacterTemplateCreator creator = view.createNewCharacter();
      creator.useEnvironment(environment);
      model.createNew(creator, environment);
    });
  }
}