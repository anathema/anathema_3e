package net.sf.anathema.hero.charms.compiler.json;

import net.sf.anathema.magic.data.reference.CharmName;
import net.sf.anathema.magic.template.CharmTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

public class TemplateMap {
  private final Map<CharmName, CharmTemplate> map = new HashMap<>();

  public void put(CharmName charmName, CharmTemplate charmTemplate) {
    map.put(charmName, charmTemplate);
  }


  public CharmTemplate getClonedTemplate(CharmName name) {
    return map.get(name).clone();
  }
  public void forEach(BiConsumer<CharmName, CharmTemplate> consumer){
    map.forEach(consumer);
  }
}