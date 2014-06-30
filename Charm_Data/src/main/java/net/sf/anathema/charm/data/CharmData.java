package net.sf.anathema.charm.data;

import com.sun.jdi.CharType;
import net.sf.anathema.charm.data.reference.CharmReference;
import net.sf.anathema.charm.parser.template.CharmTemplate;

public class CharmData {

  private CharmTemplate template;
  private CharmReference charmReference;

  public CharmData(CharmTemplate template, CharmReference charmReference) {
    this.template = template;
    this.charmReference = charmReference;
  }

  public CharmReference getCharmReference() {
    return charmReference;
  }
 }
