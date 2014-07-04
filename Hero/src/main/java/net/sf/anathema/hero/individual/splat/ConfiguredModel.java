package net.sf.anathema.hero.individual.splat;

import net.sf.anathema.library.identifier.Identifier;
import net.sf.anathema.library.identifier.SimpleIdentifier;
import net.sf.anathema.library.lang.ReflectionEqualsObject;

public class ConfiguredModel extends ReflectionEqualsObject{

  public Identifier modelId;
  public String modelTemplateId;

  public ConfiguredModel(String modelId, String modelTemplateId) {
    this.modelId = new SimpleIdentifier(modelId);
    this.modelTemplateId = modelTemplateId;
  }
 }
