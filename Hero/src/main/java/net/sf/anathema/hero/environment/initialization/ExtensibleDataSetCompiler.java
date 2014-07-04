package net.sf.anathema.hero.environment.initialization;

import net.sf.anathema.library.resources.ResourceFile;

public interface ExtensibleDataSetCompiler {
  String getName();

  String getRecognitionPattern();

  void registerFile(ResourceFile resource);

  ExtensibleDataSet build();
}