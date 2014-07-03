package net.sf.anathema.hero.framework.reporting;

public class ReportException extends RuntimeException {

  public ReportException() {
    super();
  }

  public ReportException(String message) {
    super(message);
  }

  public ReportException(String message, Throwable cause) {
    super(message, cause);
  }

  public ReportException(Throwable cause) {
    super(cause);
  }
}
