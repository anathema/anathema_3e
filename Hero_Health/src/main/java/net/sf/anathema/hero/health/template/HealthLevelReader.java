package net.sf.anathema.hero.health.template;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import net.sf.anathema.hero.health.model.HealthLevelType;

import java.io.IOException;

public class HealthLevelReader extends TypeAdapter<HealthLevelType> {
  @Override
  public void write(JsonWriter out, HealthLevelType value) throws IOException {
    //nothing to do
  }

  @Override
  public HealthLevelType read(JsonReader in) throws IOException {
    String gameNotation = in.nextString();
    return HealthLevelType.byGameNotation(gameNotation);
  }
}
