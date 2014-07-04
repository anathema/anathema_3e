package net.sf.anathema.hero.framework;

import net.sf.anathema.hero.framework.data.ExtensibleDataSet;
import net.sf.anathema.hero.framework.data.IExtensibleDataSetCompiler;
import net.sf.anathema.library.initialization.ObjectFactory;
import net.sf.anathema.library.resources.ResourceFile;
import net.sf.anathema.platform.initialization.ExtensibleDataSetCompiler;

@ExtensibleDataSetCompiler
public class CharacterTemplateResourceCompiler implements IExtensibleDataSetCompiler {

  private static final String CHARACTER_FILE_RECOGNITION_PATTERN = ".+?\\.splat";

  private final SimpleCharacterTemplateCache templateResources = new SimpleCharacterTemplateCache();

  @SuppressWarnings("UnusedParameters")
  public CharacterTemplateResourceCompiler(ObjectFactory objectFactory) {
    //nothing to do
  }

  @Override
  public String getName() {
    return "Character templates";
  }

  @Override
  public String getRecognitionPattern() {
    return CHARACTER_FILE_RECOGNITION_PATTERN;
  }

  @Override
  public void registerFile(ResourceFile resource) {
    templateResources.add(resource);
  }

  @Override
  public ExtensibleDataSet build() {
    return templateResources;
  }
}