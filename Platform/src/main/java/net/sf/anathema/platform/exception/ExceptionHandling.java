package net.sf.anathema.platform.exception;

public class ExceptionHandling {

  private final ExtensibleExceptionHandler handler = new ExtensibleExceptionHandler();

  public ExtensibleExceptionHandler create() {
    attachForThreadUncaughtExceptionHandling();
    handler.addHandler(new ConsoleExceptionHandler());
    handler.addHandler(new LoggingExceptionHandler());
    return handler;
  }

  private void attachForThreadUncaughtExceptionHandling() {
    Thread.setDefaultUncaughtExceptionHandler((t, e) -> handler.handle(e));
  }
}
