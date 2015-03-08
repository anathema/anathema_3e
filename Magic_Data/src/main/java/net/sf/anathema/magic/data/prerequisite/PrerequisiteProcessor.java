package net.sf.anathema.magic.data.prerequisite;

import net.sf.anathema.magic.data.Charm;
import net.sf.anathema.magic.data.reference.CategoryReference;
import net.sf.anathema.magic.data.reference.TreeReference;
import net.sf.anathema.magic.data.attribute.MagicAttribute;

import java.util.List;

public interface PrerequisiteProcessor {

  void requiresMagicAttributes(MagicAttribute attribute, int count);
  
  void requiresMagicAttributesFromTree(TreeReference tree, MagicAttribute attribute, int count);

  void requiresCharm(Charm prerequisite);

  void requiresCharmFromSelection(Charm[] prerequisites, int count);
  
  void requiresCharmsOfTraits(List<RequiredTraitType> traits, CategoryReference category, int count, int minimumEssence);
  
  void requiresCharmsOfAnyOneTrait(int count);
}