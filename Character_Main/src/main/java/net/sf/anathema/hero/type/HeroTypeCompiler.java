package net.sf.anathema.hero.type;

import net.sf.anathema.framework.environment.ObjectFactory;
import net.sf.anathema.framework.environment.dependencies.Weight;
import net.sf.anathema.framework.environment.resources.ResourceFile;
import net.sf.anathema.hero.framework.data.ExtensibleDataSet;
import net.sf.anathema.hero.framework.data.IExtensibleDataSetCompiler;
import net.sf.anathema.hero.framework.type.CharacterType;
import net.sf.anathema.hero.template.GenericTemplateLoader;
import net.sf.anathema.initialization.ExtensibleDataSetCompiler;
import net.sf.anathema.lib.exception.PersistenceException;

import java.io.IOException;
import java.io.InputStream;

@Weight(weight = 1)
@ExtensibleDataSetCompiler
public class HeroTypeCompiler implements IExtensibleDataSetCompiler {

  private static final String TEMPLATE_FILE_RECOGNITION_PATTERN = ".+?\\.charactertype";
  private final ExtensibleCharacterTypes types = new ExtensibleCharacterTypes();
  private final GenericTemplateLoader<SimpleCharacterType> loader = new GenericTemplateLoader<>(SimpleCharacterType.class);

  @SuppressWarnings("UnusedParameters")
  public HeroTypeCompiler(ObjectFactory objectFactory) {
    //nothing to do
  }

  @Override
  public String getName() {
    return "Character types";
  }

  @Override
  public String getRecognitionPattern() {
    return TEMPLATE_FILE_RECOGNITION_PATTERN;
  }

  @Override
  public void registerFile(ResourceFile resource) {
    try (InputStream inputStream = resource.getURL().openStream()) {
      CharacterType type = loader.load(inputStream);
      types.add(type);
    } catch (IOException e) {
      throw new PersistenceException(e);
    }
  }

  @Override
  public ExtensibleDataSet build() {
    return types;
  }
}