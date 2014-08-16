package net.sf.anathema.hero.application;

import net.sf.anathema.hero.environment.initialization.ExtensibleDataSet;
import net.sf.anathema.hero.environment.initialization.ExtensibleDataSetCompiler;
import net.sf.anathema.library.resources.ResourceFile;

@net.sf.anathema.platform.initialization.ExtensibleDataSetCompiler
public class CharacterTemplateResourceCompiler implements ExtensibleDataSetCompiler {

  private static final String CHARACTER_FILE_RECOGNITION_PATTERN = ".+?\\.splat";

  private final SimpleCharacterTemplateCache templateResources = new SimpleCharacterTemplateCache();

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