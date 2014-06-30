package net.sf.anathema.hero.template;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.io.IOUtils;

import java.net.URL;

public class HeroTemplateGson {
  private Gson gson = new GsonBuilder().create();

  public ParsedHeroTemplate fromJson(URL url) {
    try {
      String json = IOUtils.toString(url.openStream());
      return gson.fromJson(json, ParsedHeroTemplate.class);
    } catch (Exception e) {
      throw new RuntimeException("Error parsing: " + url.toExternalForm(), e);
    }
  }
}
