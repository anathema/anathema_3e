package net.sf.anathema.hero.application;

import net.sf.anathema.library.resources.ResourceFile;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SimpleCharacterTemplateCache implements CharacterTemplateResources {

  private final List<ResourceFile> templateResources = new ArrayList<>();

  public void add(ResourceFile resource) {
    templateResources.add(resource);
  }

  @Override
  public Iterator<ResourceFile> iterator() {
    return templateResources.iterator();
  }
}