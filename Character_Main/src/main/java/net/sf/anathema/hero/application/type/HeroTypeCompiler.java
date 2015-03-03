package net.sf.anathema.hero.application.type;

import net.sf.anathema.hero.environment.initialization.ExtensibleDataSet;
import net.sf.anathema.hero.environment.initialization.ExtensibleDataSetCompiler;
import net.sf.anathema.hero.individual.persistence.GenericTemplateLoader;
import net.sf.anathema.hero.individual.splat.HeroType;
import net.sf.anathema.library.initialization.Weight;
import net.sf.anathema.library.resources.ResourceFile;

@Weight(weight = 1)
@net.sf.anathema.platform.initialization.ExtensibleDataSetCompiler
public class HeroTypeCompiler implements ExtensibleDataSetCompiler {

  private static final String TEMPLATE_FILE_RECOGNITION_PATTERN = ".+?\\.charactertype";
  private final ExtensibleHeroTypes types = new ExtensibleHeroTypes();
  private final GenericTemplateLoader<SimpleHeroType> loader = new GenericTemplateLoader<>(SimpleHeroType.class);

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
    HeroType type = loader.load(resource);
    types.add(type);
  }

  @Override
  public ExtensibleDataSet build() {
    return types;
  }
}