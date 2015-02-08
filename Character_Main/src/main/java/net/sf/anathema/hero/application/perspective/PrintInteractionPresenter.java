package net.sf.anathema.hero.application.perspective;

import net.sf.anathema.hero.application.perspective.model.ItemSelectionModel;
import net.sf.anathema.hero.application.perspective.model.ReportRegister;
import net.sf.anathema.library.interaction.model.Hotkey;
import net.sf.anathema.library.interaction.view.MenuTool;
import net.sf.anathema.library.io.SingleFileChooser;
import net.sf.anathema.library.resources.RelativePath;
import net.sf.anathema.platform.environment.Environment;

public class PrintInteractionPresenter {
  private final ItemSelectionModel model;
  private final MenuTool tool;
  private final Environment environment;
  private final SingleFileChooser fileChooser;

  public PrintInteractionPresenter(ItemSelectionModel model, MenuTool interaction, Environment environment, SingleFileChooser fileChooser) {
    this.model = model;
    this.tool = interaction;
    this.environment = environment;
    this.fileChooser = fileChooser;
  }

  public void initPresentation() {
    initializeAppearance();
    initializeEnabling();
    initializeUpdate();
    initializeCommand();
    tool.setHotkey(new Hotkey('p'));
  }

  private void initializeAppearance() {
    tool.setIcon(new RelativePath("icons/TaskBarPDF24.png"));
    tool.setTooltip(environment.getString("Anathema.Reporting.Menu.PrintItem.Name"));
    tool.setText(environment.getString("Anathema.Reporting.Menu.PrintItem.Name"));
  }

  private void initializeEnabling() {
    model.whenGetsSelection(new EnableInteraction(tool));
    tool.disable();
  }

  private void initializeUpdate() {
    model.whenGetsSelection(() -> {
      tool.clearMenu();
      ReportRegister reportRegister = new InteractionReportRegister(tool, model, environment, fileChooser);
      model.registerAllReportsOn(reportRegister, environment);
    });
  }

  private void initializeCommand() {
    tool.setCommand(() -> model.printCurrentItemQuickly(environment));
  }
}
