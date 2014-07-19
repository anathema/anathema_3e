package net.sf.anathema.hero.intimacies.sheet.content;

import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.intimacies.model.Bond;
import net.sf.anathema.hero.intimacies.model.IntimaciesModel;
import net.sf.anathema.hero.intimacies.model.IntimaciesModelFetcher;
import net.sf.anathema.hero.intimacies.model.Intimacy;
import net.sf.anathema.hero.sheet.pdf.content.AbstractSubBoxContent;
import net.sf.anathema.hero.sheet.pdf.content.ListSubBoxContent;
import net.sf.anathema.library.resources.Resources;

import java.util.ArrayList;
import java.util.List;

import static net.sf.anathema.hero.intimacies.model.Outlook.Negative;

public class SimpleIntimaciesContent extends AbstractSubBoxContent implements ListSubBoxContent {

  private Hero hero;

  public SimpleIntimaciesContent(Hero hero, Resources resources) {
    super(resources);
    this.hero = hero;
  }

  @Override
  public String getHeaderKey() {
    return "Intimacies";
  }

  @Override
  public boolean useNewLineForEachEntry() {
    return true;
  }

  @Override
  public List<String> getPrintEntries() {
    List<String> printIntimacies = new ArrayList<>();
    for (Intimacy intimacy : getModel().getEntries()) {
      String text = getTextForIntimacy(intimacy);
      printIntimacies.add(text);
    }
    return printIntimacies;
  }

  private String getTextForIntimacy(Intimacy intimacy) {
    String text = intimacy.getName();
    String outlook = getOutlookString(intimacy);
    String strength = getStrengthString(intimacy);
    String bond = getBondString(intimacy);
    text += " (" + outlook + strength + bond + ")";
    return text;
  }

  private String getBondString(Intimacy intimacy) {
    if (intimacy.getBond() == Bond.Principle) {
      return getResources().getString("Intimacies.Sheet.Bond.Principle");
    }
    return getResources().getString("Intimacies.Sheet.Bond.Tie");
  }

  private String getStrengthString(Intimacy intimacy) {
    switch (intimacy.getStrength()) {
      case Major:
        return getResources().getString("Intimacies.Sheet.Strength.Major");
      case Minor:
        return getResources().getString("Intimacies.Sheet.Strength.Minor");
      default:
        return getResources().getString("Intimacies.Sheet.Strength.Defining");
    }
  }

  private String getOutlookString(Intimacy intimacy) {
    if (intimacy.getOutlook() == Negative) {
      return getResources().getString("Intimacies.Sheet.Outlook.Negative");
    }
    return getResources().getString("Intimacies.Sheet.Outlook.Positive");
  }

  private IntimaciesModel getModel() {
    return IntimaciesModelFetcher.fetch(hero);
  }
}
