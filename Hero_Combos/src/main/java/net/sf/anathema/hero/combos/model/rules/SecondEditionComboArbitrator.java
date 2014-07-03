package net.sf.anathema.hero.combos.model.rules;

import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.charm.data.CharmAttributeList;

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