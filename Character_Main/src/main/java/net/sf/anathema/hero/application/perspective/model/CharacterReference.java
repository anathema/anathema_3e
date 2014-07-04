package net.sf.anathema.hero.application.perspective.model;

import net.sf.anathema.platform.repository.printname.RepositoryId;

public class CharacterReference {

  public final RepositoryId repositoryId;
  public final String printName;

  public CharacterReference(RepositoryId repositoryId, String printName) {
    this.repositoryId = repositoryId;
    this.printName = printName;
  }
}