package net.sf.anathema.magic.data.source;

import net.sf.anathema.library.lang.ReflectionEqualsObject;

public class SourceBookImpl extends ReflectionEqualsObject implements SourceBook {

  private String id;

  public SourceBookImpl(String id) {
    this.id = id;
  }

  @Override
  public String getId() {
    return id;
  }
}
