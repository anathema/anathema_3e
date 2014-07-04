package net.sf.anathema.hero.framework.data;

import net.sf.anathema.library.resources.ResourceFile;

public interface IExtensibleDataSetCompiler {
  String getName();

  String getRecognitionPattern();

  void registerFile(ResourceFile resource);

  ExtensibleDataSet build();
}