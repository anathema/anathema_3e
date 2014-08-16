package net.sf.anathema.charm.template.prerequisite;

import com.google.common.collect.Lists;
import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.charm.data.prerequisite.CharmPrerequisite;
import net.sf.anathema.charm.data.prerequisite.RequiredTraitType;
import net.sf.anathema.charm.data.prerequisite.TraitGroupCharmPrerequisite;
import net.sf.anathema.charm.data.reference.CharmName;
import net.sf.anathema.platform.persistence.JsonType;

import java.util.List;
import java.util.Map;

@JsonType("numberOfTraitCharms")
public class TraitGroupCharmPrerequisiteTemplate implements CharmPrerequisiteTemplate {

  public List<String> traits;
  public int count;
  public int minimumEssence = 1;

  @Override
  public CharmPrerequisite generate(Map<CharmName, Charm> charms) {
    return new TraitGroupCharmPrerequisite(Lists.transform(traits, RequiredTraitType::new), count, minimumEssence);
  }
}
