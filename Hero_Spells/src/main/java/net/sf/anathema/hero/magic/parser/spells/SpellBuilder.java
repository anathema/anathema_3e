package net.sf.anathema.hero.magic.parser.spells;

import net.sf.anathema.charm.data.reference.MagicName;
import net.sf.anathema.charm.old.cost.CostList;
import net.sf.anathema.charm.old.source.SourceBook;
import net.sf.anathema.charm.old.source.SourceList;
import net.sf.anathema.charm.old.source.SourceListImpl;
import net.sf.anathema.charm.parser.cost.CostListBuilder;
import net.sf.anathema.charm.parser.cost.ICostListBuilder;
import net.sf.anathema.charm.parser.source.SourceBuilder;
import net.sf.anathema.hero.magic.spells.CircleType;
import net.sf.anathema.hero.magic.spells.Spell;
import net.sf.anathema.hero.magic.spells.SpellImpl;
import net.sf.anathema.lib.exception.PersistenceException;
import org.dom4j.Document;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.List;

public class SpellBuilder {
  private final ICostListBuilder costListBuilder = new CostListBuilder();
  private final SourceBuilder sourceBuilder = new SourceBuilder();

  public Spell[] buildSpells(Document spellDocument) throws PersistenceException {
    Element spellListElement = spellDocument.getRootElement();
    List<Spell> spellList = new ArrayList<>();
    for (Object spellObject : spellListElement.elements("spell")) {
      Element spellElement = (Element) spellObject;
      buildSpell(spellElement, spellList);
    }
    return spellList.toArray(new Spell[spellList.size()]);
  }

  private void buildSpell(Element spellElement, List<Spell> spellList) throws PersistenceException {
    String id = spellElement.attributeValue("id");
    String circleId = spellElement.attributeValue("circle");
    CostList temporaryCost = costListBuilder.buildCostList(spellElement.element("cost"));
    Element targetElement = spellElement.element("target");
    String target = null;
    if (targetElement != null) {
      target = targetElement.attributeValue("target");
    }
    SourceList sourceList = buildSource(spellElement);
    if (sourceList.isEmpty()) {
      return;
    }
    spellList.add(new SpellImpl(new MagicName(id), CircleType.valueOf(circleId), temporaryCost, sourceList, target));
  }

  private SourceList buildSource(Element spellElement) {
    SourceBook[] sources = sourceBuilder.buildSourceList(spellElement);
    SourceListImpl sourceList = new SourceListImpl();
    for (SourceBook source : sources) {
      sourceList.addSource(source);
    }
    return sourceList;
  }
}