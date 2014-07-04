package net.sf.anathema.namegenerator.domain.general;

public class ConcatenatedNameTokenFactory implements INameTokenFactory {

  public INameTokenFactory[] tokenFactories;

  public ConcatenatedNameTokenFactory(INameTokenFactory[] tokenFactories) {
    this.tokenFactories = tokenFactories;
  }

  @Override
  public String createToken() {
    StringBuilder token = new StringBuilder();
    boolean isFirstToken = true;
    for (INameTokenFactory factory : tokenFactories) {
      String tokenPart = factory.createToken();
      if (isFirstToken) {
        tokenPart = capitalize(tokenPart);
        isFirstToken = false;
      }
      token.append(tokenPart);
    }
    return token.toString();
  }

  private String capitalize(String tokenPart) {
    return Character.toTitleCase(tokenPart.charAt(0)) + tokenPart.substring(1);
  }
}