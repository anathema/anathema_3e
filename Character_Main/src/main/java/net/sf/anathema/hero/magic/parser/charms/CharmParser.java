package net.sf.anathema.hero.magic.parser.charms;

import net.sf.anathema.charm.parser.template.special.SpecialCharmTemplate;
import net.sf.anathema.hero.magic.charm.CharmImpl;
import net.sf.anathema.lib.exception.PersistenceException;
import org.dom4j.Element;

import java.util.List;

public interface CharmParser {

  CharmImpl buildCharm(Element charmElement, List<SpecialCharmTemplate> specialCharms) throws PersistenceException;
}