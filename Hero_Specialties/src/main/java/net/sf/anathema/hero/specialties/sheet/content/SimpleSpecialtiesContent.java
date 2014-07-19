package net.sf.anathema.hero.specialties.sheet.content;

import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.sheet.pdf.content.ListSubBoxContent;
import net.sf.anathema.hero.specialties.model.ISubTraitContainer;
import net.sf.anathema.hero.specialties.model.SpecialtiesModel;
import net.sf.anathema.hero.specialties.model.SpecialtiesModelFetcher;
import net.sf.anathema.hero.specialties.model.Specialty;
import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.hero.traits.sheet.content.TraitReferenceI18n;
import net.sf.anathema.library.resources.Resources;

import java.util.ArrayList;
import java.util.List;

public class SimpleSpecialtiesContent implements ListSubBoxContent {

  private Hero hero;
  private Resources resources;

  public SimpleSpecialtiesContent(Hero hero, Resources resources) {
    this.hero = hero;
    this.resources = resources;
  }

  @Override
  public List<String> getPrintEntries() {
    List<String> printEntries = new ArrayList<>();
    SpecialtiesModel specialtiesModel = SpecialtiesModelFetcher.fetch(hero);
    TraitReferenceI18n traitI18n = new TraitReferenceI18n(resources);
    // todo sort by ability name
    for (TraitType type : specialtiesModel.getAllParentTraits()) {
      ISubTraitContainer specialtiesContainer = specialtiesModel.getSpecialtiesContainer(type);
      for (Specialty specialty : specialtiesContainer.getSubTraits()) {
        printEntries.add(traitI18n.getSheetName(new NamedSpecialtyReference(specialty, type)));
      }
    }
    return printEntries;
  }

  @Override
  public boolean useNewLineForEachEntry() {
    return false;
  }

  @Override
  public String getHeader() {
    return resources.getString("Sheet.Header.Specialties");
  }

  @Override
  public boolean hasContent() {
    return true;
  }
}
