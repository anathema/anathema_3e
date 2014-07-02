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
import net.sf.anathema.hero.magic.parser.charms.CharmAlternativeParser;
import net.sf.anathema.hero.magic.parser.charms.CharmMergedParser;
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
public class CharmCompiler implements IExtensibleDataSetCompiler {
  private static final String Charm_File_Recognition_Pattern = "Charms.*\\.xml";
  //matches stuff like data/charms/solar/Charms_Solar_SecondEdition_Occult.xml
  //the pattern is data/charms/REST_OF_PATH/Charms_TYPE_EDITION_ANYTHING.xml
  private static final String Charm_Data_Extraction_Pattern = ".*/Charms_(.*?)_(.*?)(?:_.*)?\\.xml";
  private final CharmAlternativeParser alternativeBuilder = new CharmAlternativeParser();
  private final CharmMergedParser mergedBuilder = new CharmMergedParser();
  private final SAXReader reader = new SAXReader();
  private final CharmCacheImpl charmCache;
  private final CharmSetBuilder setBuilder;
  private final CharmDocuments charmDocuments = new CharmDocuments();

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
    buildCharmAlternatives();
    buildCharmMerges();
    charmCache.extractParents();
    return charmCache;
  }

  private void buildStandardCharms() throws PersistenceException {
    charmDocuments.forEach((document, category) -> {
      List<SpecialCharmDto> specialCharms = new ArrayList<>();
      CharmImpl[] charmArray = setBuilder.buildCharms(document, specialCharms);
      for (CharmImpl charm : charmArray) {
        charmCache.addCharm(category, charm);
      }
      charmCache.addSpecialCharmData(category, specialCharms);
    });
  }

  private void buildCharmAlternatives() {
    charmDocuments.forEach((document, category) -> alternativeBuilder.buildAlternatives(document,
            charmCache.getCharms(category)));
  }

  private void buildCharmMerges() {
    charmDocuments.forEach((document, category) -> mergedBuilder.buildMerges(document, charmCache.getCharms(category)));
  }

}