package net.sf.anathema.hero.merits.sheet.content;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.merits.model.Merit;
import net.sf.anathema.hero.merits.model.MeritsModel;
import net.sf.anathema.hero.merits.model.MeritsModelFetcher;
import net.sf.anathema.hero.sheet.pdf.content.AbstractSubBoxContent;
import net.sf.anathema.hero.sheet.pdf.content.ListSubBoxContent;
import net.sf.anathema.library.resources.Resources;

public class SimpleMeritsContent extends AbstractSubBoxContent implements ListSubBoxContent {

  private Hero hero;

  public SimpleMeritsContent(Hero hero, Resources resources) {
    super(resources);
    this.hero = hero;
  }

  @Override
  public String getHeaderKey() {
    return "Merits";
  }

  @Override
  public boolean useNewLineForEachEntry() {
    return true;
  }

  @Override
  public List<String> getPrintEntries() {
    List<String> printMerits = new ArrayList<>();
    for (Merit merit : getModel().getEntries()) {
      String text = getTextForMerit(merit);
      printMerits.add(text);
    }
    return printMerits;
  }

  private String getTextForMerit(Merit merit) {
    String text = merit.getBaseOption().getId();
    //text += " (" + outlook + strength + bond + ")";
    return text;
  }

  private MeritsModel getModel() {
    return MeritsModelFetcher.fetch(hero);
  }
}
