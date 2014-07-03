package net.sf.anathema.magic.source;

public class SourceListImpl implements SourceList {

  private SourceBook primarySource;

  @Override
  public SourceBook getPrimarySource() {
    return primarySource;
  }

  @Override
  public boolean isEmpty() {
    return primarySource == null;
  }

  public void addSource(SourceBook source) {
    if (primarySource == null) {
      this.primarySource = source;
    }
  }
}