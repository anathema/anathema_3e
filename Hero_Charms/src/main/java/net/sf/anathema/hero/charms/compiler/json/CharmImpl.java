package net.sf.anathema.hero.charms.compiler.json;

import net.sf.anathema.magic.data.Charm;
import net.sf.anathema.magic.data.CharmType;
import net.sf.anathema.magic.data.Duration;
import net.sf.anathema.magic.data.PrerequisiteList;
import net.sf.anathema.magic.data.cost.CostList;
import net.sf.anathema.magic.data.prerequisite.CharmPrerequisite;
import net.sf.anathema.magic.data.reference.CategoryReference;
import net.sf.anathema.magic.data.reference.CharmName;
import net.sf.anathema.magic.data.reference.TreeName;
import net.sf.anathema.magic.data.reference.TreeReference;
import net.sf.anathema.magic.template.CharmTemplate;
import net.sf.anathema.library.identifier.SimpleIdentifier;
import net.sf.anathema.magic.data.AbstractMagic;
import net.sf.anathema.magic.data.attribute.MagicAttributeImpl;
import net.sf.anathema.magic.data.source.SourceBook;
import net.sf.anathema.magic.data.source.SourceBookImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class CharmImpl extends AbstractMagic implements Charm {

  private List<Charm> children = new ArrayList<>();
  private PrerequisiteListImpl prerequisiteList;
  private final CategoryReference category;
  private final TreeName tree;
  private CharmName name;
  private final CharmTemplate template;

  public CharmImpl(CategoryReference category, TreeName tree, CharmName name, CharmTemplate template) {
    this.category = category;
    this.tree = tree;
    this.name = name;
    this.template = template;
    this.prerequisiteList = new PrerequisiteListImpl(template);
    template.keywords.forEach(tag -> addMagicAttribute(new MagicAttributeImpl(tag, true)));
    template.internalTags.forEach(tag -> addMagicAttribute(new MagicAttributeImpl(tag, false)));
  }

  @Override
  public CharmName getName() {
    return name;
  }

  @Override
  public TreeReference getTreeReference() {
    return new TreeReference(category, tree);
  }

  @Override
  public List<SourceBook> getSources() {
    Stream<String> sourceStrings = template.sources.stream();
    return sourceStrings.map(SourceBookImpl::new).collect(toList());
  }

  @Override
  public CharmType getCharmType() {
    for (CharmType type: CharmType.values()) {
      if (hasAttribute(new SimpleIdentifier(type.name()))) {
        return type;
      }
    }
    return CharmType.Simple;
  }

  @Override
  public Duration getDuration() {
    return new Duration(template.duration);
  }

  @Override
  public CostList getTemporaryCost() {
    return new CostParser().parse(template.cost);
  }

  @Override
  public void forEachChild(Consumer<Charm> consumer) {
    children.stream().forEach(consumer);
  }

  @Override
  public PrerequisiteList getPrerequisites() {
    return prerequisiteList;
  }

  public void addChild(CharmImpl charm) {
    children.add(charm);
  }

  public void addCharmPrerequisite(CharmPrerequisite prerequisite) {
    prerequisiteList.addCharmPrerequisite(prerequisite);
  }
  
  public void clearPrerequisites() {
  	prerequisiteList.clearPrerequisites();
  }
}
