package net.sf.anathema.character.ghost.passions;

import net.sf.anathema.character.generic.template.additional.IAdditionalTemplate;

public class GhostPassionsTemplate implements IAdditionalTemplate {
  public static final String ID = "Ghost.Passions.Template";

  @Override
  public String getId() {
    return ID;
  }

  public GhostPassionsTemplate()
  {
  }
}