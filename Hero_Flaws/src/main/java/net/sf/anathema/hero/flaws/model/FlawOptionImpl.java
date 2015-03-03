package net.sf.anathema.hero.flaws.model;

import net.sf.anathema.hero.flaws.compiler.template.FlawTemplate;
import net.sf.anathema.library.model.property.AbstractOptionalPropertyOption;

public class FlawOptionImpl extends AbstractOptionalPropertyOption implements FlawOption {

  public FlawOptionImpl(FlawTemplate template) {
    super(template.name);
  }
}
