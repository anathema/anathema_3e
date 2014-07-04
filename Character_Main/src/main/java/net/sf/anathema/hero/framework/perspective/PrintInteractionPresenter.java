package net.sf.anathema.hero.framework.perspective;

import net.sf.anathema.hero.framework.perspective.model.ItemSelectionModel;
import net.sf.anathema.hero.framework.perspective.model.ReportRegister;
import net.sf.anathema.library.interaction.view.MenuTool;
import net.sf.anathema.library.io.SingleFileChooser;
import net.sf.anathema.library.resources.RelativePath;
import net.sf.anathema.platform.environment.Environment;

public class PrintInteractionPresenter {
  private final ItemSelectionModel model;
  private final MenuTool interaction;
  private final Environment environment;
  private final SingleFileChooser fileChooser;

  public PrintInteractionPresenter(ItemSelectionModel model, MenuTool interaction, Environment environment, SingleFileChooser fileChooser) {
    this.model = model;
    this.interaction = interaction;
    this.environment = environment;
    this.fileChooser = fileChooser;
  }

  public void initPresentation() {
    initializeAppearance();
    initializeEnabling();
    initializeUpdate();
    initializeCommand();
  }

  private void initializeAppearance() {
    interaction.setIcon(new RelativePath("icons/TaskBarPDF24.png"));
    interaction.setTooltip(environment.getString("Anathema.Reporting.Menu.PrintItem.Name"));
  }

  private void initializeEnabling() {
    model.whenGetsSelection(new EnableInteraction(interaction));
    interaction.disable();
  }

  private void initializeUpdate() {
    model.whenGetsSelection(() -> {
      interaction.clearMenu();
      ReportRegister reportRegister = new InteractionReportRegister(interaction, model, environment, fileChooser);
      model.registerAllReportsOn(reportRegister, environment);
    });
  }

  private void initializeCommand() {
    interaction.setCommand(() -> model.printCurrentItemQuickly(environment));
  }
}
