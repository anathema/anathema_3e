package net.sf.anathema.library.exception;

public class NotYetImplementedException extends UnsupportedOperationException {

  public NotYetImplementedException() {
    super("Not yet implemented.");
  }
}