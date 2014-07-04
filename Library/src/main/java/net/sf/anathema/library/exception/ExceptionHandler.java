package net.sf.anathema.library.exception;

public interface ExceptionHandler {

  void handle(Throwable exception);

  void handle(Throwable exception, String message);
}