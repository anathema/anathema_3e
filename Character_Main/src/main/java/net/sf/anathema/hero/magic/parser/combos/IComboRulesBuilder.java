package net.sf.anathema.hero.magic.parser.combos;

import net.sf.anathema.hero.magic.charm.CharmException;
import net.sf.anathema.hero.magic.charm.combos.IComboRestrictions;
import org.dom4j.Element;

public interface IComboRulesBuilder {

  IComboRestrictions buildComboRules(Element rulesElement) throws CharmException;
}