package net.sf.anathema.hero.application.perspective;

import net.sf.anathema.hero.application.item.HeroReferenceScanner;
import net.sf.anathema.hero.application.persistence.HeroMainFileDto;
import net.sf.anathema.hero.application.persistence.HeroMainFilePersister;
import net.sf.anathema.hero.application.perspective.model.CharacterReference;
import net.sf.anathema.hero.environment.herotype.HeroTypes;
import net.sf.anathema.hero.environment.template.SplatTypeImpl;
import net.sf.anathema.hero.individual.splat.HeroType;
import net.sf.anathema.hero.individual.splat.SplatType;
import net.sf.anathema.library.identifier.Identifier;
import net.sf.anathema.library.identifier.SimpleIdentifier;
import net.sf.anathema.platform.repository.IRepositoryFileResolver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static net.sf.anathema.hero.application.item.HeroItemTypeRetrieval.retrieveCharacterItemType;
import static net.sf.anathema.hero.concept.model.concept.CasteType.NULL_CASTE_TYPE;

public class JsonHeroReferenceScanner implements HeroReferenceScanner {

  private final Map<CharacterReference, SplatType> typesByFile = new HashMap<>();
  private final Map<CharacterReference, Identifier> castesByFile = new HashMap<>();
  private final IRepositoryFileResolver resolver;
  private final HeroTypes heroTypes;

  public JsonHeroReferenceScanner(HeroTypes heroTypes, IRepositoryFileResolver repositoryFileResolver) {
    this.heroTypes = heroTypes;
    this.resolver = repositoryFileResolver;
  }

  private void scan(CharacterReference reference) throws IOException {
    File scanFile = resolver.getMainFile(retrieveCharacterItemType().getRepositoryConfiguration(), reference.repositoryId.getStringRepresentation());
    try (FileInputStream stream = new FileInputStream(scanFile)) {
      HeroMainFileDto mainFileDto = new HeroMainFilePersister().load(stream);
      HeroType heroType = heroTypes.findById(mainFileDto.characterType.characterType);
      SimpleIdentifier subType = new SimpleIdentifier(mainFileDto.characterType.subType);
      typesByFile.put(reference, new SplatTypeImpl(heroType, subType));
      castesByFile.put(reference, NULL_CASTE_TYPE);
    }
  }

  @Override
  public HeroType getCharacterType(CharacterReference reference) {
    SplatType splatType = getTemplateType(reference);
    if (splatType == null) {
      return null;
    }
    return splatType.getHeroType();
  }

  @Override
  public SplatType getTemplateType(CharacterReference reference) {
    if (typesByFile.containsKey(reference)) {
      return typesByFile.get(reference);
    }
    try {
      scan(reference);
      return typesByFile.get(reference);
    } catch (IOException e) {
      return null;
    }
  }

  @Override
  public Identifier getCasteType(CharacterReference reference) {
    if (castesByFile.containsKey(reference)) {
      return castesByFile.get(reference);
    }
    try {
      scan(reference);
      return castesByFile.get(reference);
    } catch (IOException e) {
      return NULL_CASTE_TYPE;
    }
  }
}