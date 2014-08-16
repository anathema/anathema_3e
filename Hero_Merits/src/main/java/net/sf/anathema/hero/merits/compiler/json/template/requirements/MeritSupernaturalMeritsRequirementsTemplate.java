package net.sf.anathema.hero.merits.compiler.json.template.requirements;

import net.sf.anathema.hero.merits.model.requirements.MeritRequirement;
import net.sf.anathema.hero.merits.model.requirements.MeritSupernaturalMeritsRequirement;
import net.sf.anathema.platform.persistence.JsonType;

@JsonType("supernaturalMerits")
public class MeritSupernaturalMeritsRequirementsTemplate implements MeritRequirementsTemplate {

  @Override
  public MeritRequirement generate() {
    return new MeritSupernaturalMeritsRequirement();
  }
}
