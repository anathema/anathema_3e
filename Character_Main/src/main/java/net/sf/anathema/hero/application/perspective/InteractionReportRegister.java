package net.sf.anathema.hero.application.perspective;

import net.sf.anathema.hero.application.perspective.model.ItemSelectionModel;
import net.sf.anathema.hero.application.perspective.model.ReportRegister;
import net.sf.anathema.hero.environment.report.Report;
import net.sf.anathema.library.interaction.model.Tool;
import net.sf.anathema.library.interaction.view.MenuTool;
import net.sf.anathema.library.io.SingleFileChooser;
import net.sf.anathema.platform.environment.Environment;

public class InteractionReportRegister implements ReportRegister {
  private final MenuTool interaction;
  private final ItemSelectionModel model;
  private final Environment environment;
  private final SingleFileChooser fileChooser;

  public InteractionReportRegister(MenuTool interaction, ItemSelectionModel model, Environment environment, SingleFileChooser uiEnvironment) {
    this.interaction = interaction;
    this.model = model;
    this.environment = environment;
    this.fileChooser = uiEnvironment;
  }

  @Override
  public void register(final Report report) {
    Tool tool = interaction.addMenuEntry();
    tool.setText(report.toString());
    tool.setCommand(() -> model.printCurrentItemInto(report, environment, fileChooser));
  }
}