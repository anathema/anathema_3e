package net.sf.anathema.hero.application.perspective;

import net.sf.anathema.hero.application.perspective.model.ItemSelectionModel;
import net.sf.anathema.library.interaction.view.InteractionView;
import net.sf.anathema.library.io.SingleFileChooser;
import net.sf.anathema.platform.environment.Environment;

public class BackInteractionPresenter {

  private ItemSelectionModel model;
  private InteractionView view;
  private final Environment environment;
  private final SingleFileChooser fileChooser;

  public BackInteractionPresenter(ItemSelectionModel model, InteractionView view, Environment environment,
                                  SingleFileChooser fileChooser) {
    this.model = model;
    this.view = view;
    this.environment = environment;
    this.fileChooser = fileChooser;
  }

  public void initPresentation() {
    initSaveInteraction();
    initControlledPrintInteraction();
    initExperiencedInteraction();
  }

  private void initSaveInteraction() {
    new SaveInteractionPresenter(model, view.addTool(), environment).initPresentation();
  }

  private void initControlledPrintInteraction() {
    new PrintInteractionPresenter(model, view.addMenuTool(), environment, fileChooser).initPresentation();
  }

  private void initExperiencedInteraction() {
    new ExperiencedInteractionPresenter(model, view.addToggleTool(), environment).initPresentation();
  }
}