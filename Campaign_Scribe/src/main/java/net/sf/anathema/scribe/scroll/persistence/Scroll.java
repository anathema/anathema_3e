package net.sf.anathema.scribe.scroll.persistence;

import net.sf.anathema.platform.repository.printname.RepositoryId;

public class Scroll {

  public final ScrollDto dto;
  public final RepositoryId repositoryId;

  public Scroll(ScrollDto dto, RepositoryId repositoryId) {
    this.dto = dto;
    this.repositoryId = repositoryId;
  }
}
