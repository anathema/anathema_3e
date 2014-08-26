package net.sf.anathema.charm.data.prerequisite;

import java.util.List;

import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.charm.data.reference.CategoryReference;
import net.sf.anathema.charm.data.reference.TreeReference;
import net.sf.anathema.magic.data.attribute.MagicAttribute;

public interface PrerequisiteProcessor {

  void requiresMagicAttributes(MagicAttribute attribute, int count);
  
  void requiresMagicAttributesFromTree(TreeReference tree, MagicAttribute attribute, int count);

  void requiresCharm(Charm prerequisite);

  void requiresCharmFromSelection(Charm[] prerequisites, int threshold);
  
  void requiresCharmsOfTraits(List<RequiredTraitType> traits, CategoryReference category, int threshold, int minimumEssence);
  
  void requiresCharmsOfAnyOneTrait(int threshold);
}
