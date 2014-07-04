package net.sf.anathema.hero.application.repositorytree;

import net.sf.anathema.hero.application.CharacterUI;
import net.sf.anathema.hero.application.creation.CharacterTypeUi;
import net.sf.anathema.hero.application.item.HeroReferenceScanner;
import net.sf.anathema.hero.application.perspective.model.CharacterReference;
import net.sf.anathema.hero.elsewhere.concept.CasteType;
import net.sf.anathema.hero.individual.splat.CharacterType;
import net.sf.anathema.library.identifier.Identifier;
import net.sf.anathema.library.presenter.AbstractUIConfiguration;
import net.sf.anathema.library.resources.RelativePath;
import net.sf.anathema.library.resources.Resources;
import net.sf.anathema.platform.repository.PrintNameFile;
import net.sf.anathema.platform.repository.printname.SimpleRepositoryId;

public class HeroFileUi extends AbstractUIConfiguration<PrintNameFile> {

  private final Resources resources;
  private final HeroReferenceScanner scanner;

  public HeroFileUi(Resources resources, HeroReferenceScanner scanner) {
    this.resources = resources;
    this.scanner = scanner;
  }

  @Override
  public RelativePath getIconsRelativePath(PrintNameFile value) {
    CharacterType characterType = scanner.getCharacterType(createReference(value));
    return new CharacterUI().getSmallTypeIconPath(characterType);
  }

  @Override
  public String getLabel(PrintNameFile value) {
    String printName = value.getPrintName();
    CharacterReference reference = createReference(value);
    CharacterType characterType = scanner.getCharacterType(reference);
    String characterString = new CharacterTypeUi(resources).getLabel(characterType);
    Identifier casteType = scanner.getCasteType(reference);
    if (casteType == CasteType.NULL_CASTE_TYPE) {
      return resources.getString("LoadCharacter.PrintNameFile.ShortMessage", printName, characterString);
    }
    String casteTypeString = resources.getString("Caste." + casteType.getId());
    String casteString = resources.getString(characterType.getId() + ".Caste.Label");
    return resources.getString("LoadCharacter.PrintNameFile.Message", printName, characterString, casteTypeString, casteString);
  }

  private CharacterReference createReference(PrintNameFile value) {
    return new CharacterReference(new SimpleRepositoryId(value.getRepositoryId()), value.getPrintName());
  }
}