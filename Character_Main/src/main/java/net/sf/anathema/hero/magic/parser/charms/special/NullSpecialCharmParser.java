package net.sf.anathema.hero.magic.parser.charms.special;

import net.sf.anathema.charm.parser.template.special.SpecialCharmTemplate;
import net.sf.anathema.framework.environment.dependencies.DoNotInstantiateAutomatically;
import org.dom4j.Element;

@DoNotInstantiateAutomatically
public class NullSpecialCharmParser implements  SpecialCharmParser {
  @Override
  public void parse(Element charmElement, SpecialCharmTemplate overallDto) {
    // nothing to do
  }

  @Override
  public boolean supports(Element charmElement) {
    return false;
  }
}
