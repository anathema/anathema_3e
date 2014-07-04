package net.sf.anathema.hero.environment.template;

import net.sf.anathema.library.identifier.Identifier;

public interface TemplateFactory {

  <T> T loadModelTemplate(Identifier modelId, TemplateLoader<T> loader);
}
