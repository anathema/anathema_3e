package net.sf.anathema.charm.data.prerequisite;

import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.charm.data.reference.CategoryReference;
import net.sf.anathema.charm.data.reference.TreeReference;
import net.sf.anathema.magic.data.attribute.MagicAttribute;

import java.util.List;

public class PrerequisiteProcessorAdapter implements PrerequisiteProcessor{
  @Override
  public void requiresMagicAttributes(MagicAttribute attribute, int count) {
    //nothing to do
  }

  @Override
  public void requiresMagicAttributesFromTree(TreeReference tree, MagicAttribute attribute, int count) {
    //nothing to do
  }

  @Override
  public void requiresCharm(Charm prerequisite) {
    //nothing to do
  }

  @Override
  public void requiresCharmFromSelection(Charm[] prerequisites, int threshold) {
    //nothing to do
  }

  @Override
  public void requiresCharmsOfTraits(List<RequiredTraitType> traits, CategoryReference category, int threshold, int minimumEssence) {
    //nothing to do
  }

  @Override
  public void requiresCharmsOfAnyOneTrait(int threshold) {
    //nothing to do
  }
}
