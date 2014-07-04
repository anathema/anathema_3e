package net.sf.anathema.platform.environment;

public interface ExceptionHandler {
  void handle(Throwable exception);

  void handle(Throwable exception, String message);
}