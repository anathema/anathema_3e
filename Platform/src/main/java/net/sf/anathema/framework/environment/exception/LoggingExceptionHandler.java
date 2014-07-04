package net.sf.anathema.framework.environment.exception;

import net.sf.anathema.library.logging.Logger;
import net.sf.anathema.platform.environment.ExceptionHandler;

public class LoggingExceptionHandler implements ExceptionHandler {
  private static final Logger logger = Logger.getLogger(LoggingExceptionHandler.class);

  @Override
  public void handle(Throwable exception) {
    logger.error("Uncaught Exception:", exception);
  }

  @Override
  public void handle(Throwable exception, String message) {
    logger.error(message, exception);
  }
}