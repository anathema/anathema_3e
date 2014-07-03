package net.sf.anathema.hero.combos.model.rules;

import net.sf.anathema.charm.data.CharmAttributeList;
import net.sf.anathema.hero.magic.charm.Charm;

public class SecondEditionComboArbitrator extends AbstractComboArbitrator {

  @Override
  protected boolean isCharmLegalByRules(Charm charm) {
    boolean comboBasic = isComboBasic(charm);
    boolean comboOk = charm.hasAttribute(CharmAttributeList.COMBO_OK_ATTRIBUTE);
    return comboBasic || comboOk;
  }

  private boolean isComboBasic(Charm charm) {
    return charm.hasAttribute(CharmAttributeList.COMBO_BASIC_ATTRIBUTE);
  }
}