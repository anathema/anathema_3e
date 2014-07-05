package net.sf.anathema.hero.application.environment;

public class TestInjectionInstance {

  @Inject
  public String annotatedString;
  @Inject
  public Integer annotatedInteger;
  @Inject
  public Number annotatedNumber;

  public String unannotatedString;
}
