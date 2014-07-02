package net.sf.anathema.hero.charms.compiler;

import net.sf.anathema.charm.data.reference.CategoryReference;
import net.sf.anathema.framework.environment.ObjectFactory;
import net.sf.anathema.framework.environment.resources.ResourceFile;
import net.sf.anathema.hero.charms.compiler.special.ReflectionSpecialCharmBuilder;
import net.sf.anathema.hero.framework.data.ExtensibleDataSet;
import net.sf.anathema.hero.framework.data.IExtensibleDataSetCompiler;
import net.sf.anathema.hero.framework.data.IExtensibleDataSetProvider;
import net.sf.anathema.hero.framework.type.CharacterTypes;
import net.sf.anathema.hero.magic.charm.CharmException;
import net.sf.anathema.hero.magic.charm.CharmImpl;
import net.sf.anathema.hero.magic.parser.charms.CharmAlternativeBuilder;
import net.sf.anathema.hero.magic.parser.charms.CharmMergedBuilder;
import net.sf.anathema.hero.magic.parser.charms.CharmSetBuilder;
import net.sf.anathema.hero.magic.parser.charms.special.ReflectionSpecialCharmParser;
import net.sf.anathema.hero.magic.parser.dto.special.SpecialCharmDto;
import net.sf.anathema.initialization.ExtensibleDataSetCompiler;
import net.sf.anathema.lib.exception.PersistenceException;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@ExtensibleDataSetCompiler
public class CharmCompiler implements IExtensibleDataSetCompiler {
  private static final String Charm_File_Recognition_Pattern = "Charms.*\\.xml";
  //matches stuff like data/charms/solar/Charms_Solar_SecondEdition_Occult.xml
  //the pattern is data/charms/REST_OF_PATH/Charms_TYPE_EDITION_ANYTHING.xml
  private static final String Charm_Data_Extraction_Pattern = ".*/Charms_(.*?)_(.*?)(?:_.*)?\\.xml";
  private final Map<CategoryReference, List<Document>> charmFileTable = new HashMap<>();
  private final CharmAlternativeBuilder alternativeBuilder = new CharmAlternativeBuilder();
  private final CharmMergedBuilder mergedBuilder = new CharmMergedBuilder();
  private final SAXReader reader = new SAXReader();
  private final CharmCacheImpl charmCache;
  private final CharmSetBuilder setBuilder;

  public CharmCompiler(ObjectFactory objectFactory, IExtensibleDataSetProvider provider) {
    ReflectionSpecialCharmBuilder specialCharmBuilder = new ReflectionSpecialCharmBuilder(objectFactory);
    ReflectionSpecialCharmParser specialCharmParser = new ReflectionSpecialCharmParser(objectFactory);
    this.charmCache = new CharmCacheImpl(specialCharmBuilder);
    CharacterTypes characterTypes = provider.getDataSet(CharacterTypes.class);
    this.setBuilder = new CharmSetBuilder(characterTypes, specialCharmParser);
  }

  @Override
  public String getName() {
    return "Charms";
  }

  @Override
  public String getRecognitionPattern() {
    return Charm_File_Recognition_Pattern;
  }

  @SuppressWarnings("ResultOfMethodCallIgnored")
  @Override
  public void registerFile(ResourceFile resource) throws Exception {
    Matcher matcher = Pattern.compile(Charm_Data_Extraction_Pattern).matcher(resource.getFileName());
    matcher.matches();
    String categoryString = matcher.group(1);
    CategoryReference category = new CategoryReference(categoryString);
    List<Document> list = charmFileTable.get(category);
    if (list == null) {
      list = new ArrayList<>();
      charmFileTable.put(category, list);
    }
    try {
      list.add(reader.read(resource.getURL()));
    } catch (DocumentException e) {
      throw new CharmException(resource.getURL().toExternalForm(), e);
    }
  }

  @Override
  public ExtensibleDataSet build() throws PersistenceException {
    for (CategoryReference type : charmFileTable.keySet()) {
      buildStandardCharms(type);
      buildCharmAlternatives(type);
      buildCharmMerges(type);
    }
    charmCache.extractParents();
    return charmCache;
  }

  private void buildStandardCharms(CategoryReference category) throws PersistenceException {
    List<Document> documents = charmFileTable.get(category);
    for (Document charmDocument : documents) {
      buildTypeCharms(category, charmDocument);
    }
  }

  private void buildCharmAlternatives(CategoryReference type) {
    if (charmFileTable.containsKey(type)) {
      for (Document charmDocument : charmFileTable.get(type)) {
        alternativeBuilder.buildAlternatives(charmDocument, charmCache.getCharms(type));
      }
    }
  }

  private void buildCharmMerges(CategoryReference type) {
    if (charmFileTable.containsKey(type)) {
      for (Document charmDocument : charmFileTable.get(type)) {
        mergedBuilder.buildMerges(charmDocument, charmCache.getCharms(type));
      }
    }
  }

  private void buildTypeCharms(CategoryReference type, Document charmDocument) throws PersistenceException {
    List<SpecialCharmDto> specialCharms = new ArrayList<>();
    CharmImpl[] charmArray = setBuilder.buildCharms(charmDocument, specialCharms);
    for (CharmImpl charm : charmArray) {
      charmCache.addCharm(type, charm);
    }
    charmCache.addSpecialCharmData(type, specialCharms);
  }
}