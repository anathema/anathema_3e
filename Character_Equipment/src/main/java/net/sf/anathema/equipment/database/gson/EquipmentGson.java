package net.sf.anathema.equipment.database.gson;

import java.lang.reflect.Type;

import net.sf.anathema.equipment.stats.IEquipmentStats;
import net.sf.anathema.equipment.stats.IWeaponTag;
import net.sf.anathema.equipment.stats.impl.WeaponTag;
import net.sf.anathema.equipment.template.EquipmentTemplate;
import net.sf.anathema.equipment.template.IEquipmentTemplate;
import net.sf.anathema.library.identifier.Identifier;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class EquipmentGson {

  private Gson gson;

  public EquipmentGson() {
    GsonBuilder gsonBuilder = new GsonBuilder();
    gsonBuilder.registerTypeAdapter(IEquipmentStats.class, new StatsAdapter());
    gsonBuilder.registerTypeHierarchyAdapter(IWeaponTag.class, new WeaponTagAdapter());
    gsonBuilder.registerTypeAdapter(Identifier.class, new IdentificateAdapter());
    gsonBuilder.registerTypeAdapter(String.class, new EmptyStringAdapter());
    gsonBuilder.setPrettyPrinting();
    gson = gsonBuilder.create();
  }

  public String toJson(IEquipmentTemplate template) {
    return gson.toJson(template);
  }

  public IEquipmentTemplate fromJson(String json) {
    return gson.fromJson(json, EquipmentTemplate.class);
  }

  private class WeaponTagAdapter implements JsonDeserializer {
    @Override
    public IWeaponTag deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
      return WeaponTag.valueOf(json.getAsString());
    }
  }
}