package net.sf.anathema.hero.framework;

import net.sf.anathema.hero.framework.data.ExtensibleDataSet;
import net.sf.anathema.hero.framework.data.IExtensibleDataSetProvider;
import net.sf.anathema.hero.framework.type.CharacterTypes;
import net.sf.anathema.hero.template.TemplateRegistry;
import net.sf.anathema.hero.template.TemplateRegistryImpl;
import net.sf.anathema.initialization.repository.DataFileProvider;
import net.sf.anathema.library.initialization.ObjectFactory;

public class HeroEnvironmentImpl implements HeroEnvironment {

  private final TemplateRegistry templateRegistry = new TemplateRegistryImpl();
  private final DataFileProvider dataFileProvider;
  private final IExtensibleDataSetProvider dataSetProvider;
  private final ObjectFactory objectFactory;
  private final CharacterTypes characterTypes;

  public HeroEnvironmentImpl(DataFileProvider dataFileProvider, ObjectFactory objectFactory,
                             IExtensibleDataSetProvider dataSetProvider) {
    this.objectFactory = objectFactory;
    this.dataFileProvider = dataFileProvider;
    this.dataSetProvider = dataSetProvider;
    this.characterTypes = dataSetProvider.getDataSet(CharacterTypes.class);
  }

  @Override
  public TemplateRegistry getTemplateRegistry() {
    return templateRegistry;
  }

  @Override
  public ObjectFactory getObjectFactory() {
    return objectFactory;
  }

  @Override
  public CharacterTypes getCharacterTypes() {
    return characterTypes;
  }

  @Override
  public DataFileProvider getDataFileProvider() {
    return dataFileProvider;
  }

  @Override
  public <T extends ExtensibleDataSet> T getDataSet(Class<T> set) {
    return dataSetProvider.getDataSet(set);
  }
}
