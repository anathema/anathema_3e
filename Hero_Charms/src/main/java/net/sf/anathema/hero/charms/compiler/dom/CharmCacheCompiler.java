package net.sf.anathema.hero.charms.compiler.dom;

import net.sf.anathema.charm.data.reference.CategoryReference;
import net.sf.anathema.framework.environment.ObjectFactory;
import net.sf.anathema.framework.environment.resources.ResourceFile;
import net.sf.anathema.hero.charms.compiler.special.ReflectionSpecialCharmBuilder;
import net.sf.anathema.hero.charms.model.special.ISpecialCharm;
import net.sf.anathema.hero.framework.data.ExtensibleDataSet;
import net.sf.anathema.hero.framework.data.IExtensibleDataSetCompiler;
import net.sf.anathema.hero.framework.data.IExtensibleDataSetProvider;
import net.sf.anathema.hero.framework.type.CharacterTypes;
import net.sf.anathema.hero.magic.charm.CharmException;
import net.sf.anathema.hero.magic.charm.CharmImpl;
import net.sf.anathema.hero.magic.parser.charms.CharmSetBuilder;
import net.sf.anathema.hero.magic.parser.charms.special.ReflectionSpecialCharmParser;
import net.sf.anathema.hero.magic.parser.dto.special.SpecialCharmDto;
import net.sf.anathema.initialization.ExtensibleDataSetCompiler;
import net.sf.anathema.lib.exception.PersistenceException;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@ExtensibleDataSetCompiler
public class CharmCacheCompiler implements IExtensibleDataSetCompiler {
  private static final String Charm_File_Recognition_Pattern = "Charms.*\\.xml";
  //matches stuff like data/charms/solar/Charms_Solar_SecondEdition_Occult.xml
  //the pattern is data/charms/REST_OF_PATH/Charms_TYPE_EDITION_ANYTHING.xml
  private static final String Charm_Data_Extraction_Pattern = ".*/Charms_(.*?)_(.*?)(?:_.*)?\\.xml";
  private final SAXReader reader = new SAXReader();
  private final CharmSetBuilder setBuilder;
  private final CharmDocuments charmDocuments = new CharmDocuments();
  private final UnlinkedCharms charmCollection = new UnlinkedCharms();
  private final ReflectionSpecialCharmBuilder specialCharmBuilder;

  public CharmCacheCompiler(ObjectFactory objectFactory, IExtensibleDataSetProvider provider) {
    this.specialCharmBuilder = new ReflectionSpecialCharmBuilder(objectFactory);
    ReflectionSpecialCharmParser specialCharmParser = new ReflectionSpecialCharmParser(objectFactory);
    CharacterTypes characterTypes = provider.getDataSet(CharacterTypes.class);
    this.setBuilder = new CharmSetBuilder(characterTypes, specialCharmParser);
  }

  @Override
  public String getName() {
    return "OldCharms";
  }

  @Override
  public String getRecognitionPattern() {
    return Charm_File_Recognition_Pattern;
  }

  @SuppressWarnings("ResultOfMethodCallIgnored")
  @Override
  public void registerFile(ResourceFile resource) throws Exception {
    CategoryReference category = getCategoryFromResourceName(resource);
    try {
      Document document = reader.read(resource.getURL());
      charmDocuments.addDocument(category, document);
    } catch (DocumentException e) {
      throw new CharmException(resource.getURL().toExternalForm(), e);
    }
  }

  private CategoryReference getCategoryFromResourceName(ResourceFile resource) {
    Matcher matcher = Pattern.compile(Charm_Data_Extraction_Pattern).matcher(resource.getFileName());
    matcher.matches();
    String categoryString = matcher.group(1);
    return new CategoryReference(categoryString);
  }

  @Override
  public ExtensibleDataSet build() throws PersistenceException {
    buildStandardCharms();
    //return charmCollection.createCharmCache();
    return null;
  }

  private void buildStandardCharms() throws PersistenceException {
    charmDocuments.forEach((document, category) -> {
      List<SpecialCharmDto> specialCharmDtos = new ArrayList<>();
      CharmImpl[] charmArray = setBuilder.buildCharms(document, specialCharmDtos);
      for (CharmImpl charm : charmArray) {
        charmCollection.addCharm(category, charm);
      }
      buildSpecialCharms(category, specialCharmDtos);
    });
  }

  private void buildSpecialCharms(CategoryReference reference, List<SpecialCharmDto> dtos) {
    if (dtos == null) {
      return;
    }
    List<ISpecialCharm> specialCharms = new ArrayList<>();
    for (SpecialCharmDto dto : dtos) {
      specialCharms.add(specialCharmBuilder.readCharm(dto));
    }
    charmCollection.addSpecialCharmData(reference, specialCharms);
  }
}