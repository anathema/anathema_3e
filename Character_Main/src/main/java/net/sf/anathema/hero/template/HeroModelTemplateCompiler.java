package net.sf.anathema.hero.template;

import net.sf.anathema.hero.environment.initialization.ExtensibleDataSet;
import net.sf.anathema.hero.environment.initialization.ExtensibleDataSetCompiler;
import net.sf.anathema.library.initialization.ObjectFactory;
import net.sf.anathema.library.resources.ResourceFile;

@net.sf.anathema.platform.initialization.ExtensibleDataSetCompiler
public class HeroModelTemplateCompiler implements ExtensibleDataSetCompiler {

  private static final String TEMPLATE_FILE_RECOGNITION_PATTERN = ".+?\\.template";
  private HeroModelTemplateCache templates = new HeroModelTemplateCache();


  @SuppressWarnings("UnusedParameters")
  public HeroModelTemplateCompiler(ObjectFactory objectFactory) {
    //nothing to do
  }

  @Override
  public String getName() {
    return "Character model template extensions";
  }

  @Override
  public String getRecognitionPattern() {
    return TEMPLATE_FILE_RECOGNITION_PATTERN;
  }

  @Override
  public void registerFile(ResourceFile resource) {
    templates.add(resource);
  }

  @Override
  public ExtensibleDataSet build() {
    return templates;
  }
}