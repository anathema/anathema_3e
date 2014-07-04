package net.sf.anathema.framework.presenter.action.menu.help.updatecheck;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class UrlLoader {

  private final String urlString;

  public UrlLoader(String url) {
    this.urlString = url;
  }

  public String readAll() throws IOException {
    String response;
    try (InputStream input = new URL(urlString).openStream()) {
      response = IOUtils.toString(input);
    }
    return response;
  }
}