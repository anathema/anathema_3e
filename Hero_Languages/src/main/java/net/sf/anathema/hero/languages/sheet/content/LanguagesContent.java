package net.sf.anathema.hero.languages.sheet.content;

import java.util.List;
import java.util.stream.Collectors;

import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.merits.model.Merit;
import net.sf.anathema.hero.merits.model.MeritsModel;
import net.sf.anathema.hero.merits.model.MeritsModelFetcher;
import net.sf.anathema.hero.sheet.pdf.content.AbstractSubBoxContent;
import net.sf.anathema.hero.sheet.pdf.content.ListSubBoxContent;
import net.sf.anathema.library.model.OptionalEntryReference;
import net.sf.anathema.library.resources.Resources;

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
    MeritsModel model = MeritsModelFetcher.fetch(hero);
    List<Merit> languageMerits = model.getMeritsMatchingReference(new OptionalEntryReference("Language"));
    return languageMerits.stream().map(Merit::getDescription).collect(Collectors.toList());
  }

  @Override
  public boolean useNewLineForEachEntry() {
    return false;
  }
}