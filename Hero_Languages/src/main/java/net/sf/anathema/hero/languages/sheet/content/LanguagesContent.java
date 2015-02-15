package net.sf.anathema.hero.languages.sheet.content;

import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.merits.model.Merit;
import net.sf.anathema.hero.merits.model.MeritReference;
import net.sf.anathema.hero.merits.model.MeritsModel;
import net.sf.anathema.hero.merits.model.MeritsModelFetcher;
import net.sf.anathema.hero.sheet.pdf.content.AbstractSubBoxContent;
import net.sf.anathema.hero.sheet.pdf.content.ListSubBoxContent;
import net.sf.anathema.library.resources.Resources;

import java.util.ArrayList;
import java.util.List;

public class LanguagesContent extends AbstractSubBoxContent implements ListSubBoxContent {

  private Hero hero;

  public LanguagesContent(Hero hero, Resources resources) {
    super(resources);
    this.hero = hero;
  }

  @Override
  public String getHeaderKey() {
    return "Languages";
  }

  @Override
  public List<String> getPrintEntries() {
    List<String> printLanguages = new ArrayList<>();
    MeritsModel model = getModel();
    List<Merit> languageMerits = model.getMeritsMatchingReference(new MeritReference("Language"));
    for (Merit languageMerit : languageMerits) {
      printLanguages.add(languageMerit.getDescription());
    }
    return printLanguages;
  }

  @Override
  public boolean useNewLineForEachEntry() {
    return false;
  }

  private MeritsModel getModel() {
    return MeritsModelFetcher.fetch(hero);
  }
}
