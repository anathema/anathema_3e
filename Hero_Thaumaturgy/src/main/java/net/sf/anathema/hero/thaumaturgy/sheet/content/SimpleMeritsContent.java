package net.sf.anathema.hero.thaumaturgy.sheet.content;

import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.sheet.pdf.content.AbstractSubBoxContent;
import net.sf.anathema.hero.sheet.pdf.content.ListSubBoxContent;
import net.sf.anathema.hero.thaumaturgy.model.KnownRitual;
import net.sf.anathema.hero.thaumaturgy.model.ThaumaturgyModelFetcher;
import net.sf.anathema.library.resources.Resources;

import java.util.ArrayList;
import java.util.List;

public class SimpleMeritsContent extends AbstractSubBoxContent implements ListSubBoxContent {

  private Hero hero;

  public SimpleMeritsContent(Hero hero, Resources resources) {
    super(resources);
    this.hero = hero;
  }

  @Override
  public String getHeaderKey() {
    return "Thaumaturgy";
  }

  @Override
  public boolean useNewLineForEachEntry() {
    return true;
  }

  @Override
  public List<String> getPrintEntries() {
    List<String> printMerits = new ArrayList<>();
    for (KnownRitual merit : ThaumaturgyModelFetcher.fetch(hero).getEntries()) {
      String text = getTextForMerit(merit);
      printMerits.add(text);
    }
    return printMerits;
  }

  private String getTextForMerit(KnownRitual merit) {
    String id = merit.getBaseOption().getTraitType().getId();
    return id + " (" + merit.getDescription() + ")";
  }
}