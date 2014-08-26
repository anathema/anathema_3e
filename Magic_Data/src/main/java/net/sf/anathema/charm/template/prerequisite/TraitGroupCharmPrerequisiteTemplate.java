package net.sf.anathema.charm.template.prerequisite;

import com.google.common.collect.Lists;

import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.charm.data.prerequisite.CharmPrerequisite;
import net.sf.anathema.charm.data.prerequisite.RequiredTraitType;
import net.sf.anathema.charm.data.prerequisite.TraitGroupCharmPrerequisite;
import net.sf.anathema.charm.data.reference.CategoryReference;
import net.sf.anathema.charm.data.reference.CharmName;
import net.sf.anathema.platform.persistence.JsonType;

import java.util.List;
import java.util.Map;

@JsonType("numberOfTraitCharms")
public class TraitGroupCharmPrerequisiteTemplate implements CharmPrerequisiteTemplate {

  public List<String> options;
  public int threshold;
  public int minimumEssence = 1;
  public String category;

  @Override
  public CharmPrerequisite generate(Map<CharmName, Charm> charms) {
    return new TraitGroupCharmPrerequisite(Lists.transform(options, RequiredTraitType::new),
    		category != null ? new CategoryReference(category) : null, threshold, minimumEssence);
  }
}
