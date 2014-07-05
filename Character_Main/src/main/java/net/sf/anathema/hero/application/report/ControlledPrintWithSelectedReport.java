package net.sf.anathema.hero.application.report;

import net.sf.anathema.hero.concept.model.description.HeroDescriptionFetcher;
import net.sf.anathema.hero.environment.report.Report;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.library.io.FileChooserConfiguration;
import net.sf.anathema.library.io.FileExtension;
import net.sf.anathema.library.io.SingleFileChooser;
import net.sf.anathema.library.text.ITextualDescription;
import net.sf.anathema.platform.environment.Environment;

public class ControlledPrintWithSelectedReport {
  private final Environment environment;
  private final Hero hero;
  private final Report report;
  private final SingleFileChooser singleFileChooser;

  public ControlledPrintWithSelectedReport(Environment environment, Report report, Hero hero, SingleFileChooser singleFileChooser) {
    this.environment = environment;
    this.report = report;
    this.hero = hero;
    this.singleFileChooser = singleFileChooser;
  }

  public void execute() {
    String name = getFileNameSuggestion()+ PrintCommand.PDF_EXTENSION;
    FileChooserConfiguration configuration = new FileChooserConfiguration(new FileExtension("PDF Files", "*" + PrintCommand.PDF_EXTENSION), name);
    ControlledFileChooser fileChooser = new ControlledFileChooser(singleFileChooser, configuration);
    new PrintCommand(environment, report, fileChooser, hero).execute();
  }

  private String getFileNameSuggestion() {
    ITextualDescription nameContainer = HeroDescriptionFetcher.fetch(hero).getName();
    if (nameContainer.isEmpty()) {
      return "Character";
    }
    return nameContainer.getText();
  }
}