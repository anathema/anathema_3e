package net.sf.anathema.charm.template.prerequisite;

import net.sf.anathema.charm.data.prerequisite.CharmPrerequisite;
import net.sf.anathema.charm.data.prerequisite.RequiredTraitType;
import net.sf.anathema.charm.data.prerequisite.TraitGroupCharmPrerequisite;
import net.sf.anathema.charm.data.reference.CategoryReference;
import net.sf.anathema.platform.persistence.JsonType;

import com.google.common.collect.Lists;

import java.util.List;

@JsonType("numberOfTraitCharms")
public class TraitGroupCharmPrerequisiteTemplate implements CharmPrerequisiteTemplate {

  public List<String> options;
  public int threshold;
  public int minimumEssence = 1;
  public String category;

  @Override
  public CharmPrerequisite generate(CharmMap charms) {
    return new TraitGroupCharmPrerequisite(Lists.transform(options, RequiredTraitType::new),
    		category != null ? new CategoryReference(category) : null, threshold, minimumEssence);
  }
}
